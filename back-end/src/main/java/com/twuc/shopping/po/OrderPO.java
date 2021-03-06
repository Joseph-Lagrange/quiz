package com.twuc.shopping.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ShopOrder")
public class OrderPO {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private int price;

    private int number;

    private String unit;

    @ManyToOne
    private ProductPO productPO;
}
