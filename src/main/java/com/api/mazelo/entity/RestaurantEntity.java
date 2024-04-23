package com.api.mazelo.entity;

import com.api.mazelo.converter.StatusTypeConverter;
import com.api.mazelo.type.StatusType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Builder
@Table(name = "RESTAURANT", schema = "mazelodb")
//Need to do add the restaurant as UserDetails
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "USER_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ADDRESS")
    private String fullAddress;

    @Column(name = "PIN_CODE")
    private Long pinCode;

    @Column(name = "STATE")
    private Long state;

    @Column(name = "NEAR_BY_AREA")
    private Long nearByArea;

    @Column(name = "CONTACT")
    private String contact;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "STATUS", nullable = false)
    @Convert(converter = StatusTypeConverter.class)
    private StatusType status;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    @Column(name = "UPDATED_BY")
    @LastModifiedBy
    private String updatedBy;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    @NonNull
    private Set<RoleEntity> roles = new HashSet<>();
}
