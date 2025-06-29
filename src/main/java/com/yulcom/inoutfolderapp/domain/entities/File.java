package com.yulcom.inoutfolderapp.domain.entities;

import com.yulcom.inoutfolderapp.domain.enums.FilePriority;
import com.yulcom.inoutfolderapp.domain.enums.FileStatus;
import com.yulcom.inoutfolderapp.domain.enums.FileType;
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
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Files")
@Entity
@ToString
public class File extends BaseEntity
{

    @Enumerated(EnumType.STRING)
    private FileType type;

    @Enumerated(EnumType.STRING)
    private FilePriority priority;

    @Enumerated(EnumType.STRING)
    private FileStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Corporation assignedHandler;

    @ManyToOne(fetch = FetchType.LAZY)
    private Folder associatedFolder;

    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<FileAction> actionHistory = new ArrayList<>();

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
