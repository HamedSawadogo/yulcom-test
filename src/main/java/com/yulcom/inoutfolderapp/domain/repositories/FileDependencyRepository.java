package com.yulcom.inoutfolderapp.domain.repositories;

import com.yulcom.inoutfolderapp.domain.entities.FileDependency;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FileDependencyRepository extends JpaRepository<FileDependency, UUID>
{
    @Query("SELECT f from FileDependency f left join fetch f.fileParent where f.fileParent.id=:id")
    List<FileDependency> findAllByFileParentId(UUID id);
}
