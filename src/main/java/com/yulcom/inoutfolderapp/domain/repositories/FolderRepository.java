package com.yulcom.inoutfolderapp.domain.repositories;

import com.yulcom.inoutfolderapp.domain.entities.Folder;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface FolderRepository extends JpaRepository<Folder, UUID>
{
    @Query("SELECT f FROM Folder f " +
        "LEFT JOIN FETCH f.files " +
        "LEFT JOIN FETCH f.createdBy c " +
        "WHERE f.files IS NOT EMPTY AND c.corporation.id = :corporationId " +
        "ORDER BY f.createdAt DESC")
    Page<Folder> findAllByCorporationId(UUID corporationId, Pageable pageable);
}
