package com.yulcom.inoutfolderapp.domain.repositories;

import com.yulcom.inoutfolderapp.domain.entities.Product;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, UUID>
{
}
