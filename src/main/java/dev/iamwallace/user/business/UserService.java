package dev.iamwallace.user.business;

import dev.iamwallace.user.business.converter.UserConverter;
import dev.iamwallace.user.business.dto.UserDTO;
import dev.iamwallace.user.infrastructure.entity.User;
import dev.iamwallace.user.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final UserConverter userConverter;

  public UserDTO create(UserDTO userDTO) {
    User user = userRepository.save(userConverter.toUser(userDTO));
    return userConverter.toUserDTO(user);
  }
}
