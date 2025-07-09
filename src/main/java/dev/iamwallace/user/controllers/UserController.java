package dev.iamwallace.user.controllers;

import dev.iamwallace.user.business.UserService;
import dev.iamwallace.user.business.dto.UserDTO;
import dev.iamwallace.user.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  @PostMapping
  public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
    return ResponseEntity.ok(userService.create(userDTO));
  }

  @PostMapping("/login")
  public String login(@RequestBody UserDTO userDTO) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
    );

    return jwtUtil.generateToken(authentication.getName());
  }

  @GetMapping
  public ResponseEntity<UserDTO> getUserByEmail(@RequestParam("email") String email) {
    return ResponseEntity.ok((userService.getUserByEmail(email)));
  }

  @DeleteMapping("/{email}")
  public  ResponseEntity<Void> deleteUserByEmail(@PathVariable String email) {
    userService.deleteUserByEmail(email);
    return ResponseEntity.noContent().build();
  }

  @PutMapping
  public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO, @RequestHeader("Authorization") String token) {
    return ResponseEntity.ok(userService.update(token, userDTO));
  }
}
