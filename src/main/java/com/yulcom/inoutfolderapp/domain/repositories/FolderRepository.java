package com.yulcom.inoutfolderapp.domain.repositories;

import com.yulcom.inoutfolderapp.domain.entities.Folder;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, UUID>
{
}
