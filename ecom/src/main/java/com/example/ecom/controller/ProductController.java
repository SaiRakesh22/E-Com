package com.example.ecom.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ecom.model.Product;
import com.example.ecom.service.ProductService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK) ;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        // Implementation to fetch a product by its ID
        Product product = productService.getProductById(id);
        if(product == null) {
            return new ResponseEntity<>( new Product(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.ACCEPTED);
    }   

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, 
                                        @RequestPart MultipartFile imageFile) {
        try{
            Product product1 = productService.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>("Error while adding product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }       
    }

    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable int id){
        Product product = productService.getProductById(id);
        if(product == null || product.getImageData() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(product.getImageType()))
                    .body(product.getImageData());
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(
            @PathVariable int id,
            @RequestPart("product") Product updatedProduct,  // 👈 changed name
            @RequestPart MultipartFile imageFile) {
        Product product = null;
        try {
            product = productService.updatedProduct(id, updatedProduct, imageFile);
        } catch (IOException e) { 
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }
        if (product == null) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
    }
     
    
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        if(productService.getProductById(id) != null){
            productService.deleteProduct(id);
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Product not found", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        // Implementation to search products based on the query
        List<Product> li = productService.searchByKeyword(keyword);
        return new ResponseEntity<>(li , HttpStatus.OK);
    }
}
