package com.yulcom.inoutfolderapp.domain.repositories;

import com.yulcom.inoutfolderapp.domain.entities.File;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, UUID>
{
    List<File> findAllByAssociatedFolderIdAndAssignedHandlerId(UUID associatedFolderId, UUID assignedHandlerId);
}
