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
public class Policy {
    private String number;
    private PolicyStatus status;
    private List<PolicyObject> objects;

}
