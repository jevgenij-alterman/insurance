package com.jevgenij.insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class PolicySubObject {
    private String name;
    private double sumInsured;
    private RiskType riskType;
}
