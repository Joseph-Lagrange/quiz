package com.twuc.shopping.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.shopping.domain.Order;
import com.twuc.shopping.po.OrderPO;
import com.twuc.shopping.po.ProductPO;
import com.twuc.shopping.repository.OrderRepository;
import com.twuc.shopping.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    ObjectMapper objectMapper;

    ProductPO productPO;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        productRepository.deleteAll();
        objectMapper = new ObjectMapper();
        productPO = productRepository.save(ProductPO.builder().name("可乐").price(10).unit("瓶").url("img/img.jpg").build());
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    public void shoule_add_order_when_product_id_exist() throws Exception {
        Order order = new Order("可乐", 3, 5, "瓶");
        String jsonString = objectMapper.writeValueAsString(order);

        mockMvc.perform(post("/order/{id}", productPO.getId())
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        List<OrderPO> orderPOs = orderRepository.findAll();
        assertEquals(1, orderPOs.size());
        assertEquals("可乐", orderPOs.get(0).getName());
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    public void shoule_not_add_order_when_product_id_not_exist() throws Exception {
        Order order = new Order("可乐", 3, 5, "瓶");
        String jsonString = objectMapper.writeValueAsString(order);

        mockMvc.perform(post("/order/{id}", 100)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void should_get_all_order() throws Exception {
        Order order = new Order("可乐", 3, 5, "瓶");
        String jsonString = objectMapper.writeValueAsString(order);

        mockMvc.perform(post("/order")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/orders"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("可乐")))
                .andExpect(status().isOk());
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    public void should_delete_order_when_id_exsit() throws Exception {
        OrderPO orderPO = orderRepository.save(
                OrderPO.builder()
                        .name("可乐")
                        .price(3)
                        .number(5)
                        .unit("瓶").build());

        mockMvc.perform(delete("/order/{id}", orderPO.getId()))
                .andExpect(status().isOk());

        assertEquals(0, orderRepository.findAll().size());
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    public void should_not_delete_order_when_id_not_exsit() throws Exception {
        mockMvc.perform(delete("/order/{id}", 100))
                .andExpect(status().isBadRequest());
    }

}
