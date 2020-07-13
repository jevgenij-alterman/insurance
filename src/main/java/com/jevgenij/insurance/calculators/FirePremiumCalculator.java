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
public class FirePremiumCalculator implements Calculator {

    private static final BigDecimal DEFAULT_COEFFICIENT = BigDecimal.valueOf(0.014);
    private static final BigDecimal HIGHER_COEFFICIENT = BigDecimal.valueOf(0.024);

    public BigDecimal calculate(Policy policy) {
        double sumInsuredFire = policy.getObjects().stream().map(PolicyObject::getSubObjects)
                .flatMap(Collection::stream)
                .filter(policySubObject -> RiskType.FIRE.equals(policySubObject.getRiskType()))
                .mapToDouble(PolicySubObject::getSumInsured).sum();

        return addCoefficient(BigDecimal.valueOf(sumInsuredFire));
    }

    private BigDecimal addCoefficient(BigDecimal sumInsuredFire) {
        return sumInsuredFire.doubleValue() <= 100 ? sumInsuredFire.multiply(DEFAULT_COEFFICIENT) : sumInsuredFire.multiply(HIGHER_COEFFICIENT);
    }
}
