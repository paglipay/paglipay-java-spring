package info.paglipay.springdemo.controllers;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import info.paglipay.springdemo.models.Product;
import info.paglipay.springdemo.services.ProductServiceImpl;

@CrossOrigin
@RestController
public class ProductController {
  @Autowired
  private ProductServiceImpl ps;

  @GetMapping("/products/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable("id") String id) {
    Optional<Product> p = ps.getProductById(Integer.parseInt(id));
    return p.isPresent()
      ? new ResponseEntity<Product>(p.get(), HttpStatus.OK)
      : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
