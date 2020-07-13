package com.jevgenij.insurance.calculators;

import com.jevgenij.insurance.dto.Policy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class PremiumCalculator implements Calculator {

    private final FirePremiumCalculator firePremiumCalculator;

    private final TheftPremiumCalculator theftPremiumCalculator;

    public PremiumCalculator(FirePremiumCalculator firePremiumCalculator, TheftPremiumCalculator theftPremiumCalculator) {
        this.firePremiumCalculator = firePremiumCalculator;
        this.theftPremiumCalculator = theftPremiumCalculator;
    }

    /**
     * @param policy the policy which needs premium to be calculated
     * @return price of premium
     */
    public BigDecimal calculate(Policy policy) {
        BigDecimal firePremium = firePremiumCalculator.calculate(policy);
        log.info("Fire premium is {}", firePremium.toString());
        BigDecimal theftPremium = theftPremiumCalculator.calculate(policy);
        log.info("Theft premium is {}", theftPremium.toString());
        return firePremium.add(theftPremium);
    }
}