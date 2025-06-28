package com.yulcom.inoutfolderapp.domain.repositories;

import com.yulcom.inoutfolderapp.domain.entities.FileAction;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileActionRepository extends JpaRepository<FileAction, UUID>
{
}
