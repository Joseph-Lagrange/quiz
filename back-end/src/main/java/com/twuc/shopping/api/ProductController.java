package com.twuc.shopping.api;

import com.twuc.shopping.domain.Product;
import com.twuc.shopping.po.ProductPO;
import com.twuc.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/product")
    public ResponseEntity addProduct(@RequestBody @Valid Product product) {
        productService.save(product);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/products")
    public ResponseEntity getProducts() {
        List<ProductPO> productPOs = productService.findAll();
        return ResponseEntity.ok(productPOs);
    }


}
