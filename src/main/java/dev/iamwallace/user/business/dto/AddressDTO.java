package dev.iamwallace.user.business.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {
  private Long id;
  private String address;
  private String city;
  private String number;
  private String state;
  private String zipcode;
}
