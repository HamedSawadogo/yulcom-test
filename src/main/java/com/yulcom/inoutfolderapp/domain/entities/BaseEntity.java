package com.yulcom.inoutfolderapp.domain.entities;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;



@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "UUID")
    protected UUID id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}
