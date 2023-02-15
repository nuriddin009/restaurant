package com.example.restaurant.entity;

import com.example.restaurant.entity.enums.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Role implements GrantedAuthority {

    @Id
    private Integer id;
    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;

    @Override
    public String getAuthority() {
        return roleName.name();
    }
}
