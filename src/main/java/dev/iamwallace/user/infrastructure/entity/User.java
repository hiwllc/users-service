package dev.iamwallace.user.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// This all comes from lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// This comes from JPA (I guess)
@Entity
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name", length = 100)
  private String name;
  @Column(name = "email", length = 100)
  private String email;
  @Column(name = "password")
  private String password;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private List<Address> addresses;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private  List<dev.iamwallace.user.infrastructure.entity.Phone> phones;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }

  @Override
  public String getUsername() {
    return getEmail();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isEnabled() {
    return UserDetails.super.isEnabled();
  }
}
