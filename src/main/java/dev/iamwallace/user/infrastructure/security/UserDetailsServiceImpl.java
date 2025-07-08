package dev.iamwallace.user.infrastructure.security;

import dev.iamwallace.user.infrastructure.entity.User;
import dev.iamwallace.user.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Can't find the user"));

    return org.springframework.security.core.userdetails.User.withUsername(user.getEmail()).password(user.getPassword()).build();
  }
}
