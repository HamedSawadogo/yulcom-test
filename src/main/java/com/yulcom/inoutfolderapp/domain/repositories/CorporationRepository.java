package com.yulcom.inoutfolderapp.domain.repositories;

import com.yulcom.inoutfolderapp.domain.entities.Corporation;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporationRepository extends JpaRepository<Corporation, UUID>
{
}
