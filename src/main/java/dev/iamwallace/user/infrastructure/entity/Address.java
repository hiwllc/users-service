package dev.iamwallace.user.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
@Builder
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "address")
  private String address;

  @Column(name = "city")
  private String city;

  @Column(name = "number", length = 6)
  private String number;

  @Column(name = "state", length = 3)
  private String state;

  @Column(name = "zipcode", length = 10)
  private String zipcode;
}
