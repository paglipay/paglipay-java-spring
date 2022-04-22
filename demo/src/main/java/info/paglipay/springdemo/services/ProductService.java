package info.paglipay.springdemo.services;

import java.util.Optional;

import info.paglipay.springdemo.models.Product;

public interface ProductService {
  public Optional<Product> getProductById(int productId);
}
