package com.jevgenij.insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class PolicyObject {
    private String name;
    private List<PolicySubObject> subObjects;
}
