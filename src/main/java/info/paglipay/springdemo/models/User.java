package info.paglipay.springdemo.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a User entity in the database.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedEntityGraph(
  name = "graph.UserCartItems",
  attributeNodes = @NamedAttributeNode("itemList")
)
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private int id;

  @NotNull
  @Column(unique = true)
  private String username;

  @NotNull
  private String password;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @NotNull
  private String email;

  @NotNull
  private String phone;

  @NotNull
  private String location;

  @NotNull
  private long registrationDate;

  // Returns items in both cart and wishlist
  // Filter by CartItem's saved field to separate the lists
  @OneToMany
  @JoinColumn(name = "cart_item_id")
  @JsonIgnore
  private List<CartItem> itemList;

  public User(int id) {
    this.id = id;
  }
}
