
package com.artforyou.store.controller;

import com.artforyou.store.entity.Product;
import com.artforyou.store.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(
    origins = {
        "http://localhost:5173",
        "http://localhost:5175"
    },
    allowCredentials = "true"
)
public class ProductController {

    private final ProductRepository products;
    private final ObjectMapper mapper;

    public ProductController(
            ProductRepository products,
            ObjectMapper mapper) {
        this.products = products;
        this.mapper = mapper;
    }

 
  
    // Update product images
    @PutMapping("/{id}/images")
    public ResponseEntity<?> updateProductImages(
            @PathVariable Long id,
            @RequestBody Map<String, List<String>> data) {

        Product product = products.findById(id).orElse(null);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        List<String> images = data.get("images");

        if (images == null || images.isEmpty()) {
            return ResponseEntity.badRequest().body(
                Map.of("message", "At least one image path is required")
            );
        }

        try {
            product.setImages(mapper.writeValueAsString(images));
            products.save(product);

            return ResponseEntity.ok(Map.of(
                "message", "Product images updated successfully",
                "productId", product.getId(),
                "images", images
            ));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                Map.of("message", "Unable to update product images")
            );
        }
    }
}
