package com.yulcom.inoutfolderapp.domain.entities;

import com.yulcom.inoutfolderapp.domain.enums.FileStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Files")
@Entity
public class File extends BaseEntity
{
    private String type;

    private Integer priority;

    @Enumerated(EnumType.STRING)
    private FileStatus status;

    private Boolean isEligible;

    @ManyToOne(fetch = FetchType.LAZY)
    private Corporation assignedHandler;

    @ManyToOne(fetch = FetchType.LAZY)
    private Folder associatedFolder;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileDependency> dependencies = new ArrayList<>();

    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<FileAction> actionHistory = new ArrayList<>();

    public void addDependency(FileDependency fileDependency)
    {
        if (dependencies == null)  {
            dependencies = new ArrayList<>();
        }
        dependencies.add(fileDependency);
    }

    public void addAction(FileAction action)
    {
        if (this.actionHistory == null)
        {
            this.actionHistory = new ArrayList<>();
        }
        this.actionHistory.add(action);
        this.updatedAt = LocalDateTime.now();
    }
}
