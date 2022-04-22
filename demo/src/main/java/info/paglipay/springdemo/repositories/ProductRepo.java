package info.paglipay.springdemo.repositories;


import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import info.paglipay.springdemo.models.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product, Integer> {
  public List<Product> findByCategories(String category);
}
