package com.yulcom.inoutfolderapp.domain.entities;

import com.yulcom.inoutfolderapp.domain.enums.FileStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FileActions")
@Entity
public class FileAction extends BaseEntity
{
    @OneToOne
    private CorporateUser performedBy;
    @Enumerated(EnumType.STRING)
    private FileStatus oldStatus;
    @Enumerated(EnumType.STRING)
    private FileStatus newStatus;
    private LocalDateTime dateTime;
}
