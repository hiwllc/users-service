package dev.iamwallace.user.controllers;

import dev.iamwallace.user.business.UserService;
import dev.iamwallace.user.business.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping
  public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
    return ResponseEntity.ok(userService.create(userDTO));
  }
}
