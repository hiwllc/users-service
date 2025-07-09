package dev.iamwallace.user.business;

import dev.iamwallace.user.business.converter.UserConverter;
import dev.iamwallace.user.business.dto.UserDTO;
import dev.iamwallace.user.infrastructure.entity.User;
import dev.iamwallace.user.infrastructure.exceptions.ConflictExcepetion;
import dev.iamwallace.user.infrastructure.exceptions.ResourceNotFoundException;
import dev.iamwallace.user.infrastructure.repository.UserRepository;
import dev.iamwallace.user.infrastructure.security.JwtUtil;
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
  private final JwtUtil jwtUtil;

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

  public UserDTO getUserByEmail(String email) {
    User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User don't exists"));
    return userConverter.toUserDTO(user);
  }

  public UserDTO update(String token, UserDTO userDTO) {
    String email = jwtUtil.extractUsername(token.substring(7));

    userDTO.setPassword(userDTO.getPassword() != null ? passwordEncoder.encode(userDTO.getPassword()) : null);
    User userEntity = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User don't exists"));
    User user = userConverter.updateUser(userDTO, userEntity);

    return userConverter.toUserDTO(userRepository.save(user));
  }

  public void deleteUserByEmail(String email) {
    userRepository.deleteByEmail(email);
  }
}
