package com.currency.Backend.service;

import com.currency.Backend.model.Currency;

import com.currency.Backend.repository.CurrencyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import java.util.List;

@Service
@Slf4j
public class CurrencyService implements ICurrencyService{
    private final CurrencyRepository currencyRepository;

    @Value("${currency.url}")
    private String url;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Currency findOne(String code) {
        Currency currency =  currencyRepository.findCurrencyByCode(code);
        if (currency != null){
            return currency;
        }
        throw new EntityNotFoundException("Currency not found with code: "+code);
    }

    @Cacheable(value = "currencies",key = "#root.methodName")
    @Override
    public List<Currency> getAll() {
        log.info("reached to db not used cache");
        return currencyRepository.findAll();
    }
    @Transactional
    @Scheduled(fixedRate = 180000)
    @Override
    public void scrapeCurrencyData(){
        try {
            Document doc = Jsoup.connect(url).get();
            Element table = doc.select("#currencyResultContent").first();
            Elements rows = table.select("tr");


            for (Element row : rows) {
                Elements cells = row.select("td");
                if (cells.size() > 0) {
                    String currencyCode = cells.get(0).text();
                    String description = cells.get(1).text();
                    String buyingPrice = cells.get(2).text();
                    String sellingPrice = cells.get(3).text();
                    Currency curr = currencyRepository.findCurrencyByCode(currencyCode);
                    if(curr == null){
                        log.info("Currency Code: " + currencyCode + ", Description: " + description + ", Buying Price: " + buyingPrice + ", Selling Price: " + sellingPrice);
                        Currency currency = Currency.builder()
                                .code(currencyCode)
                                .currencyDescription(description)
                                .buyingPrice(buyingPrice)
                                .sellingPrice(sellingPrice)
                                .build();
                        currencyRepository.save(currency);
                    }
                    else {
                        curr.setBuyingPrice(buyingPrice);
                        curr.setSellingPrice(sellingPrice);
                        currencyRepository.save(curr);
                        log.info("Updated currency "+curr.getCode() + " " + curr.getBuyingPrice());
                    }


                }
            }
        }catch (IOException e){
            log.error("Exception: "+e.getMessage());
        }
    }


}
