package com.jevgenij.insurance.controller;

import com.jevgenij.insurance.calculators.PremiumCalculator;
import com.jevgenij.insurance.dto.Policy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.RoundingMode;

@RestController
public class RestApi {

    private final PremiumCalculator premiumCalculator;

    public RestApi(PremiumCalculator premiumCalculator) {
        this.premiumCalculator = premiumCalculator;
    }

    @PostMapping("/calculatePremium")
    public double calculate(@RequestBody Policy policy) {
        return premiumCalculator.calculate(policy).setScale(2, RoundingMode.UP).doubleValue();
    }
}
