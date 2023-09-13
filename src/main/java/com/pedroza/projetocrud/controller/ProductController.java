package com.pedroza.projetocrud.controller;

import com.pedroza.projetocrud.dto.ProductRecordDTO;
import com.pedroza.projetocrud.models.ProductModel;
import com.pedroza.projetocrud.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api-product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> listAll() { // já implementado
        return productService.listAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") @NotNull UUID id) {
        return productService.findById(id);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDTO productDTO) {
        return productService.save(productDTO);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> update(@PathVariable(value="id") UUID id,
                                 @RequestBody @Valid ProductRecordDTO productDTO) {
        return productService.update(id, productDTO);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value="id") UUID id) {
        return productService.delete(id);
    }

}
