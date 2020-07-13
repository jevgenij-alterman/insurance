package com.jevgenij.insurance.calculators;

import com.google.common.collect.ImmutableList;
import com.jevgenij.insurance.dto.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class PremiumCalculatorTest {
    @Test
    void calculatePremiumTest() {
        PremiumCalculator premiumCalculator = createPremiumCalculator();

        Policy policy = createPolicy();
        Assert.assertEquals(19.44752, premiumCalculator.calculate(policy).doubleValue(), 0.001);
    }

    private Policy createPolicy() {
        PolicySubObject pc = new PolicySubObject("PC", 100.00, RiskType.FIRE);
        PolicySubObject table = new PolicySubObject("table", 50.00, RiskType.THEFT);
        PolicySubObject tv = new PolicySubObject("TV", 200.99, RiskType.FIRE);
        PolicyObject flat = new PolicyObject("flat", ImmutableList.of(pc, tv, table));

        PolicySubObject car = new PolicySubObject("car", 10000.00, RiskType.THEFT);
        PolicySubObject lamp = new PolicySubObject("lamp", 20.00, RiskType.FIRE);
        PolicySubObject watch = new PolicySubObject("watch", 130, RiskType.FIRE);
        PolicyObject house = new PolicyObject("house", ImmutableList.of(pc, tv, table));

        return new Policy("1", PolicyStatus.APPROVED, ImmutableList.of(house, flat));
    }

    private PremiumCalculator createPremiumCalculator() {
        FirePremiumCalculator firePremiumCalculator = new FirePremiumCalculator();
        TheftPremiumCalculator theftPremiumCalculator = new TheftPremiumCalculator();
        return new PremiumCalculator(firePremiumCalculator, theftPremiumCalculator);

    }
}