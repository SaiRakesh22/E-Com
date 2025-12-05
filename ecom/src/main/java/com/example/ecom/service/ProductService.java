package com.example.ecom.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ecom.model.Product;
import com.example.ecom.repo.ProductRepo;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;
    public List<Product> getAllProducts() {
        return productRepo.findAll();
        // Implementation to fetch all products from the database

    }

    public Product getProductById(int id) {
        return productRepo.findById(id).orElse(null);
        // Implementation to fetch a product by its ID
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        // TODO Auto-generated method stub
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        
        return productRepo.save(product);
    }

    public Product updatedProduct(int id, Product updatedProduct, MultipartFile imageFile) throws IOException {
        updatedProduct.setImageData(imageFile.getBytes());
        updatedProduct.setImageName(imageFile.getOriginalFilename());
        updatedProduct.setImageType(imageFile.getContentType());
        return productRepo.save(updatedProduct);
    }

    public void deleteProduct(int id) {
        productRepo.deleteById(id);
    }

    public List<Product> searchByKeyword(String keyword) {
        // TODO Auto-generated method stub
        return productRepo.searchByKeyword(keyword);
    }
}
