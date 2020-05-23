package com.gkoo.open.serviceImpl;

import java.io.IOException;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.gkoo.open.data.ConfigurationData;
import com.gkoo.open.data.EstimationService;
import com.gkoo.open.exception.BuyingserviceException;
import com.gkoo.open.service.BuyingService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author sanghuncho
 *
 */
@Service
public class BuyingServiceImpl implements BuyingService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final String CURRENTCY_SERVICE_URL = "https://api.exchangeratesapi.io/latest?base=EUR";

    @Override
    public EstimationService fastEstimationBuyingService(HashMap<String, Object>[] data) {
        double productsValue = Double.parseDouble(data[0].get("productsValue").toString());
        double deliveryValue = Double.parseDouble(data[1].get("deliveryValue").toString());
        double currentEurToKRW = getCurrentEurToKrw();
        double totalPrice = productsValue + deliveryValue;
        EstimationService estimation = new EstimationService();
        estimation.setResultPrice(getEstimationBuyingService(currentEurToKRW, totalPrice));        
        return estimation;
    }
    
    private double getCurrentEurToKrw() {
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.getForEntity(CURRENTCY_SERVICE_URL, String.class);
        } catch (BuyingserviceException ex) {
            String error = "Error get Currency service";
            LOGGER.error(error, ex);
        }
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject)parser.parse(response.getBody());
        System.out.println(response.getBody());
        JsonElement jsonElement = jsonObject.get("rates");
        
        JsonObject rates = jsonElement.getAsJsonObject();
        return rates.get("KRW").getAsDouble();
    }

    public int getEstimationBuyingService(double currentEurToKRW, double totalPrice) {
        double feePercent = ConfigurationData.BUYING_SERVICE_FEE_PERCENT;
        double result = (currentEurToKRW*totalPrice)*(1 + feePercent);
    //    if (mergeBox) {
    //        double mergingBoxFee = ConfigurationData.MERGING_BOX_FEE;
    //        result = result + mergingBoxFee;
    //    }
        return mathCeilDigit(2, result);
    }

    private int mathCeilDigit(int digit, double price) {
        int power = (int) Math.pow(10, digit);
        int newPrice = (int) Math.ceil(price/power);
        return (newPrice*power);
    }
}
