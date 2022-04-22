package info.paglipay.springdemo.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.paglipay.springdemo.models.Product;
import info.paglipay.springdemo.repositories.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {
  @Autowired
  ProductRepo productRepo;

  @Override
  public Optional<Product> getProductById(int productId) {
    return productRepo.findById(productId);
  }
}
