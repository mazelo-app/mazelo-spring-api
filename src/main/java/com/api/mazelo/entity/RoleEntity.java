package com.api.mazelo.entity;

import com.api.mazelo.type.UserRoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Builder
@Table(name = "role", schema = "mazelodb")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROLE_ID", nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE_TYPE", nullable = false)
    private UserRoleType roleType;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}