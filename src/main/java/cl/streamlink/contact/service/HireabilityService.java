package cl.streamlink.contact.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HireabilityService {

    private final Logger logger = LoggerFactory.getLogger(HireabilityService.class);


    @Value("${contact.hireability.product_code}")
    private String hireAbilityProductCode;

    @Value("${contact.hireability.url}")
    private String hireAbilityUrl;



}
