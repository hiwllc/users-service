package dev.iamwallace.user.infrastructure.repository;

import dev.iamwallace.user.infrastructure.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
