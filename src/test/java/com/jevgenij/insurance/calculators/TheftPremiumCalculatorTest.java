package com.jevgenij.insurance.calculators;

import com.google.common.collect.ImmutableList;
import com.jevgenij.insurance.dto.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class TheftPremiumCalculatorTest {
    @Test
    void calculateTheftPremiumTest() {
        Policy policy = createPolicy();
        TheftPremiumCalculator theftPremiumCalculator = new TheftPremiumCalculator();
        Assert.assertEquals(7.5, theftPremiumCalculator.calculate(policy).doubleValue(), 0.001);
    }

    @Test
    void calculateEmptyTheftPremiumTest() {
        Policy policy = createEmptyTheftPolicy();
        TheftPremiumCalculator theftPremiumCalculator = new TheftPremiumCalculator();
        Assert.assertEquals(0, theftPremiumCalculator.calculate(policy).doubleValue(), 0.001);
    }

    private Policy createEmptyTheftPolicy() {
        PolicySubObject table = new PolicySubObject("table", 50.00, RiskType.FIRE);
        PolicyObject policyObject = new PolicyObject("flat", ImmutableList.of(table));
        return new Policy("1", PolicyStatus.APPROVED, Collections.singletonList(policyObject));
    }

    private Policy createPolicy() {
        PolicySubObject pc = new PolicySubObject("PC", 100.00, RiskType.THEFT);
        PolicySubObject table = new PolicySubObject("table", 50.00, RiskType.THEFT);
        PolicySubObject tv = new PolicySubObject("TV", 200.99, RiskType.FIRE);
        PolicyObject policyObject = new PolicyObject("flat", ImmutableList.of(pc, tv, table));
        return new Policy("1", PolicyStatus.APPROVED, Collections.singletonList(policyObject));
    }

}