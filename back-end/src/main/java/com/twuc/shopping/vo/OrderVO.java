package com.twuc.shopping.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {

    private int id;

    private String name;

    private int price;

    private int number;

    private String unit;

}
