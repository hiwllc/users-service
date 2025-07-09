package dev.iamwallace.user.business;

import dev.iamwallace.user.business.converter.UserConverter;
import dev.iamwallace.user.business.dto.UserDTO;
import dev.iamwallace.user.infrastructure.entity.User;
import dev.iamwallace.user.infrastructure.exceptions.ConflictExcepetion;
import dev.iamwallace.user.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final UserConverter userConverter;
  private final PasswordEncoder passwordEncoder;

  public UserDTO create(UserDTO userDTO) {
    validateUserExistenceBeforeCreating(userDTO.getEmail());

    userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

    User user = userRepository.save(userConverter.toUser(userDTO));
    return userConverter.toUserDTO(user);
  }

  public void validateUserExistenceBeforeCreating(String email) {
    if (existsByEmail(email)) {
      throw new ConflictExcepetion("User already exists");
    }
  }

  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }
}
