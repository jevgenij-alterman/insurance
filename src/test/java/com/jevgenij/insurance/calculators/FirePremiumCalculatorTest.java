package com.jevgenij.insurance.calculators;

import com.google.common.collect.ImmutableList;
import com.jevgenij.insurance.dto.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Collections;


class FirePremiumCalculatorTest {
    @Test
    void calculateFirePremiumTest() {
        Policy policy = createPolicy();
        FirePremiumCalculator firePremiumCalculator = new FirePremiumCalculator();
        Assert.assertEquals(7.22376, firePremiumCalculator.calculate(policy).doubleValue(), 0.001);
    }

    @Test
    void calculateEmptyFirePremiumTest() {
        Policy policy = createEmptyFirePolicy();
        FirePremiumCalculator firePremiumCalculator = new FirePremiumCalculator();
        Assert.assertEquals(0, firePremiumCalculator.calculate(policy).doubleValue(), 0.001);
    }

    private Policy createEmptyFirePolicy() {
        PolicySubObject table = new PolicySubObject("table", 50.00, RiskType.THEFT);
        PolicyObject policyObject = new PolicyObject("flat", ImmutableList.of(table));
        return new Policy("1", PolicyStatus.APPROVED, Collections.singletonList(policyObject));
    }

    private Policy createPolicy() {
        PolicySubObject pc = new PolicySubObject("PC", 100.00, RiskType.FIRE);
        PolicySubObject table = new PolicySubObject("table", 50.00, RiskType.THEFT);
        PolicySubObject tv = new PolicySubObject("TV", 200.99, RiskType.FIRE);
        PolicyObject policyObject = new PolicyObject("flat", ImmutableList.of(pc, tv, table));
        return new Policy("1", PolicyStatus.APPROVED, Collections.singletonList(policyObject));
    }

}