package com.twuc.shopping.api;

import com.twuc.shopping.domain.Product;
import com.twuc.shopping.po.ProductPO;
import com.twuc.shopping.service.ProductService;
import com.twuc.shopping.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/product")
    public ResponseEntity addProduct(@RequestBody @Valid Product product) {
        ProductPO productPO = productService.save(product);
        return ResponseEntity.ok(getProductVO(productPO));
    }

    private ProductVO getProductVO(ProductPO productPO) {
        return ProductVO.builder().id(productPO.getId())
                .name(productPO.getName())
                .price(productPO.getPrice())
                .unit(productPO.getUnit())
                .url(productPO.getUrl()).build();
    }

    @GetMapping("/products")
    public ResponseEntity getProducts() {
        List<ProductPO> productPOs = productService.findAll();
        List<ProductVO> productVOs = productPOs.stream().map(this::getProductVO).collect(Collectors.toList());
        return ResponseEntity.ok(productVOs);
    }


}
