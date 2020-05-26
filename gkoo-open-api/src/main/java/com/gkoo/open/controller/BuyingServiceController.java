package com.gkoo.open.controller;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.gkoo.open.configuration.ServicePath;
import com.gkoo.open.data.EstimationService;
import com.gkoo.open.service.BuyingService;

@RestController
public class BuyingServiceController {
    private static final Logger LOGGER = LogManager.getLogger();
    private final BuyingService buyingService;
    
    @Autowired
    public BuyingServiceController(BuyingService buyingService){
        this.buyingService = buyingService;
    }
    
    @CrossOrigin(origins = {ServicePath.FAST_ESTIMATION_DEV, ServicePath.FAST_ESTIMATION_PROD, ServicePath.FAST_ESTIMATION_WORLD_PROD})
    @RequestMapping(value = "/fastEstimationBuyingService", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    public EstimationService requestFastEstimationBuyingService(@RequestBody HashMap<String, Object>[] data, HttpServletRequest request) {        
        return buyingService.fastEstimationBuyingService(data);
    }
}
