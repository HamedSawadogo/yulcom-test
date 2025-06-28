package com.yulcom.inoutfolderapp.domain.entities;

import com.yulcom.inoutfolderapp.domain.enums.RoleName;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UserRoles")
@Entity
public class UserRole extends BaseEntity
{
    private RoleName name;
    private String description;
}
