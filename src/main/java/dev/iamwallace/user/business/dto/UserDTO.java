package dev.iamwallace.user.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
  private String name;
  private String email;
  //  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;
  private List<AddressDTO> addresses;
  private List<PhoneDTO> phones;
}
