package com.dw.companyapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CitiesByTotalOrderAmountDTO {
    private String city;
    private double totalAmount;

}
