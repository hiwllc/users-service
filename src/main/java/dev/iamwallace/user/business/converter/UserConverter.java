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
      .password(userDTO.getPassword())
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
      .zipcode(addressDTO.getZipcode())
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
      .password(user.getPassword())
      .addresses(toListAddressDTO(user.getAddresses()))
      .phones(toListPhonesDTO(user.getPhones()))
      .build();
  }

  public List<AddressDTO> toListAddressDTO(List<Address> address) {
    return address.stream().map(this::toAddressDTO).toList();
  }

  public AddressDTO toAddressDTO(Address address) {
    return AddressDTO.builder()
      .id(address.getId())
      .address(address.getAddress())
      .number(address.getNumber())
      .city(address.getCity())
      .state(address.getState())
      .zipcode(address.getZipcode())
      .build();
  }

  public List<PhoneDTO> toListPhonesDTO(List<Phone> phones) {
    return phones.stream().map(this::toPhoneDTO).toList();
  }

  public PhoneDTO toPhoneDTO(Phone phone) {
    return PhoneDTO.builder()
      .id(phone.getId())
      .code(phone.getCode())
      .number(phone.getNumber())
      .build();
  }

  public User updateUser(UserDTO userDTO, User user) {
    return User.builder()
      .id(user.getId())
      .name(userDTO.getName() != null ? userDTO.getName() : user.getName())
      .password(userDTO.getPassword() != null ? userDTO.getPassword() : user.getPassword())
      .email(userDTO.getEmail() != null ? userDTO.getEmail() : user.getEmail())
      .addresses(user.getAddresses())
      .phones(user.getPhones())
      .build();
  }

  public Address updateAddress(AddressDTO addressDTO, Address address) {
    return Address.builder()
      .id(address.getId())
      .address(addressDTO.getAddress() != null ? addressDTO.getAddress() : address.getAddress())
      .number(addressDTO.getNumber() != null ? addressDTO.getNumber() : address.getNumber())
      .city(addressDTO.getCity() != null ? addressDTO.getCity() : address.getCity())
      .state(addressDTO.getState() != null ? addressDTO.getState() : address.getState())
      .zipcode(addressDTO.getZipcode() != null ? addressDTO.getZipcode() : address.getZipcode())
      .build();
  }

  public Phone updatePhone(PhoneDTO phoneDTO, Phone phone) {
    return Phone.builder()
      .id(phone.getId())
      .code(phoneDTO.getCode() != null ? phoneDTO.getCode() : phone.getCode())
      .number(phoneDTO.getNumber() != null ? phoneDTO.getNumber() : phone.getNumber())
      .build();
  }

  public Address toAddressEntity(AddressDTO addressDTO, Long userId) {
    return Address.builder()
      .address(addressDTO.getAddress())
      .city(addressDTO.getCity())
      .state(addressDTO.getState())
      .number(addressDTO.getNumber())
      .zipcode(addressDTO.getZipcode())
      .user_id(userId)
      .build();
  }

  public Phone toPhoneEntity(PhoneDTO phoneDTO, Long userId) {
    return Phone.builder()
      .code(phoneDTO.getCode())
      .number(phoneDTO.getNumber())
      .user_id(userId)
      .build();
  }
}
