package com.pedroza.projetocrud.service;

import com.pedroza.projetocrud.controller.ProductController;
import com.pedroza.projetocrud.dto.ProductRecordDTO;
import com.pedroza.projetocrud.models.ProductModel;
import com.pedroza.projetocrud.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<List<ProductModel>> listAll() {
        List<ProductModel> list = productRepository.findAll();
        if (!list.isEmpty()) {
            for (ProductModel product : list) {
                UUID id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class).findById(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    public ResponseEntity<Object> findById(UUID id) {
        Optional<ProductModel> product0 = productRepository.findById(id);
        if (product0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        product0.get().add(linkTo(methodOn(ProductController.class).listAll()).withSelfRel());
        return ResponseEntity.ok().body(product0.get());
    }

    public ResponseEntity<ProductModel> save(ProductRecordDTO product) {
        ProductModel savedProduct = new ProductModel();
        BeanUtils.copyProperties(product, savedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(savedProduct));
    }

    public ResponseEntity<Object> update(UUID id, ProductRecordDTO productRecordDTO) {
        Optional<ProductModel> product0 = productRepository.findById(id);
        if (product0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        ProductModel productToUpdate = product0.get();
        BeanUtils.copyProperties(productRecordDTO, productToUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productToUpdate));
    }

    public ResponseEntity<Object> delete(UUID id) {
        Optional<ProductModel> product0 = productRepository.findById(id);
        if (product0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        productRepository.delete(product0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted sucessfully");
    }

}
