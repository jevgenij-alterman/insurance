package com.jevgenij.insurance.calculators;

import com.jevgenij.insurance.dto.Policy;

import java.math.BigDecimal;

public interface Calculator {
    BigDecimal calculate(Policy policy);
}
