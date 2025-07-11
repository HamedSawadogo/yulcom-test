package com.yulcom.inoutfolderapp.domain.entities;

import com.yulcom.inoutfolderapp.domain.enums.FileDependencyType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FilesDependencies")
@Entity
public class FileDependency extends BaseEntity
{
    private String name;
    private FileDependencyType dependencyType;
    @ManyToOne(fetch = FetchType.LAZY)
    private File fileParent;

    public FileDependency(String name, FileDependencyType dependencyType) {
        this.name = name;
        this.dependencyType = dependencyType;
    }

}

