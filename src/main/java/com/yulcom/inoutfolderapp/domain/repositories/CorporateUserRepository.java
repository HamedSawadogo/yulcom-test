package com.yulcom.inoutfolderapp.domain.repositories;

import com.yulcom.inoutfolderapp.domain.entities.CorporateUser;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateUserRepository extends JpaRepository<CorporateUser, UUID>
{
    Optional<CorporateUser> findByUsername(String username);
}
