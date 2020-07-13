package com.jevgenij.insurance.controller;

import com.google.common.collect.ImmutableList;
import com.jevgenij.insurance.calculators.FirePremiumCalculator;
import com.jevgenij.insurance.calculators.PremiumCalculator;
import com.jevgenij.insurance.calculators.TheftPremiumCalculator;
import com.jevgenij.insurance.dto.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class RestApiTest {

    @Test
    void greeting() {
        RestApi restApi = new RestApi(createPremiumCalculator());
        double calculatePremium = restApi.calculate(createPolicy());
        Assert.assertEquals(2.28, calculatePremium, 0.001);
    }

    @Test
    void greeting2() {
        RestApi restApi = new RestApi(createPremiumCalculator());
        double calculatePremium = restApi.calculate(createPolicy2());
        Assert.assertEquals(17.13, calculatePremium, 0.001);
    }

    private Policy createPolicy() {
        PolicySubObject pc = new PolicySubObject("PC", 100.00, RiskType.FIRE);
        PolicySubObject pen = new PolicySubObject("pen", 8.00, RiskType.THEFT);
        PolicyObject flat = new PolicyObject("flat", ImmutableList.of(pc, pen));
        return new Policy("1", PolicyStatus.APPROVED, Collections.singletonList(flat));
    }

    private Policy createPolicy2() {
        PolicySubObject pc = new PolicySubObject("PC", 500, RiskType.FIRE);
        PolicySubObject laptop = new PolicySubObject("laptop", 102.51, RiskType.THEFT);
        PolicyObject flat = new PolicyObject("flat", ImmutableList.of(pc, laptop));
        return new Policy("1", PolicyStatus.APPROVED, Collections.singletonList(flat));
    }

    private PremiumCalculator createPremiumCalculator() {
        FirePremiumCalculator firePremiumCalculator = new FirePremiumCalculator();
        TheftPremiumCalculator theftPremiumCalculator = new TheftPremiumCalculator();
        return new PremiumCalculator(firePremiumCalculator, theftPremiumCalculator);

    }
}