package com.twuc.shopping.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ShopProduct")
public class ProductPO {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private int price;

    private String unit;

    private String url;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "productPO")
    private List<OrderPO> orderPOs;

}
