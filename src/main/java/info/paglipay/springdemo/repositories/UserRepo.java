package info.paglipay.springdemo.repositories;

import io.micrometer.core.lang.NonNullApi;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import info.paglipay.springdemo.models.User;

@Repository
@NonNullApi
public interface UserRepo extends CrudRepository<User, Integer> {
  @EntityGraph(value = "graph.UserCartItems")
  //    @Query("SELECT u FROM USERS u WHERE lower(n.username) = lower(?1)")
  public User findByUsername(String username);

  @Override
  @EntityGraph(value = "graph.UserCartItems")
  public <S extends User> S save(S entity);

  @Override
  @EntityGraph(value = "graph.UserCartItems")
  public <S extends User> Iterable<S> saveAll(Iterable<S> entities);

  @Override
  @EntityGraph(value = "graph.UserCartItems")
  public Optional<User> findById(Integer integer);

  @Override
  @EntityGraph(value = "graph.UserCartItems")
  public Iterable<User> findAll();

  @Override
  @EntityGraph(value = "graph.UserCartItems")
  public Iterable<User> findAllById(Iterable<Integer> integers);
}
