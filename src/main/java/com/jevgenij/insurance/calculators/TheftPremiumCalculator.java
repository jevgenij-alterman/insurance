package com.jevgenij.insurance.calculators;

import com.jevgenij.insurance.dto.Policy;
import com.jevgenij.insurance.dto.PolicyObject;
import com.jevgenij.insurance.dto.PolicySubObject;
import com.jevgenij.insurance.dto.RiskType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;

@Component
@Slf4j
public class TheftPremiumCalculator implements Calculator {
    private static final BigDecimal DEFAULT_COEFFICIENT = BigDecimal.valueOf(0.11);
    private static final BigDecimal SMALLER_COEFFICIENT = BigDecimal.valueOf(0.05);

    public BigDecimal calculate(Policy policy) {
        double sumInsuredTheft = policy.getObjects().stream().map(PolicyObject::getSubObjects)
                .flatMap(Collection::stream)
                .filter(policySubObject -> RiskType.THEFT.equals(policySubObject.getRiskType()))
                .mapToDouble(PolicySubObject::getSumInsured).sum();
        return addCoefficient(sumInsuredTheft);
    }

    private BigDecimal addCoefficient(double sumInsuredTheft) {
        BigDecimal bigDecimal = BigDecimal.valueOf(sumInsuredTheft);
        return bigDecimal.doubleValue() < 15 ? bigDecimal.multiply(DEFAULT_COEFFICIENT) : bigDecimal.multiply(SMALLER_COEFFICIENT);
    }
}
