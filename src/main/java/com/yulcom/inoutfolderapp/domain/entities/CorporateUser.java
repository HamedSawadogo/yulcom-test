package com.yulcom.inoutfolderapp.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CorporateUsers")
@Entity
public class CorporateUser extends BaseEntity implements UserDetails
{
    private String username;
    private String password;
    @OneToMany(fetch = FetchType.EAGER)
    private List<UserRole> roles;
    private Boolean isActive;
    private LocalDateTime lastLoginAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Corporation corporation;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return roles.stream().map(userRole -> new SimpleGrantedAuthority(userRole.getName().name())).toList();
    }
}
