package com.dw.companyapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderCountByYearForCityDTO {
    private int year;
    private long orderCount;
}
