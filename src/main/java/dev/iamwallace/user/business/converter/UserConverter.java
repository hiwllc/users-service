package dev.iamwallace.user.business.converter;

import dev.iamwallace.user.business.dto.AddressDTO;
import dev.iamwallace.user.business.dto.PhoneDTO;
import dev.iamwallace.user.business.dto.UserDTO;
import dev.iamwallace.user.infrastructure.entity.Address;
import dev.iamwallace.user.infrastructure.entity.Phone;
import dev.iamwallace.user.infrastructure.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter {
  public User toUser(UserDTO userDTO) {
    return User.builder()
      .name(userDTO.getName())
      .email(userDTO.getEmail())
      .addresses(toListAddress(userDTO.getAddresses()))
      .phones(toListPhones(userDTO.getPhones()))
      .build();
  }

  public List<Address> toListAddress(List<AddressDTO> addressDTOS) {
    return addressDTOS.stream().map(this::toAddress).toList();
  }

  public Address toAddress(AddressDTO addressDTO) {
    return Address.builder()
      .address(addressDTO.getAddress())
      .number(addressDTO.getNumber())
      .city(addressDTO.getCity())
      .state(addressDTO.getState())
      .build();
  }

  public List<Phone> toListPhones(List<PhoneDTO> phonesDTO) {
      return phonesDTO.stream().map(this::toPhone).toList();
  }

  public Phone toPhone(PhoneDTO phoneDTO) {
    return Phone.builder()
      .code(phoneDTO.getCode())
      .number(phoneDTO.getNumber())
      .build();
  }

  public UserDTO toUserDTO(User user) {
    return UserDTO.builder()
      .name(user.getName())
      .email(user.getEmail())
      .addresses(toListAddressDTO(user.getAddresses()))
      .phones(toListPhonesDTO(user.getPhones()))
      .build();
  }

  public List<AddressDTO> toListAddressDTO(List<Address> address) {
    return address.stream().map(this::toAddressDTO).toList();
  }

  public AddressDTO toAddressDTO(Address address) {
    return AddressDTO.builder()
      .address(address.getAddress())
      .number(address.getNumber())
      .city(address.getCity())
      .state(address.getState())
      .build();
  }

  public List<PhoneDTO> toListPhonesDTO(List<Phone> phones) {
    return phones.stream().map(this::toPhoneDTO).toList();
  }

  public PhoneDTO toPhoneDTO(Phone phone) {
    return PhoneDTO.builder()
      .code(phone.getCode())
      .number(phone.getNumber())
      .build();
  }
}
