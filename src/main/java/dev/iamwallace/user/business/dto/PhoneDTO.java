package dev.iamwallace.user.business.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneDTO {
  private Long id;
  private String number;
  private String code;
}
