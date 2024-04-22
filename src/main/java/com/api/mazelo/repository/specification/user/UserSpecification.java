package com.api.mazelo.repository.specification.user;

import com.api.mazelo.entity.UserEntity;
import com.api.mazelo.type.StatusType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserSpecification {
    public Specification<UserEntity> userIdIs(Long userId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), userId);
    }

    public Specification<UserEntity> userNameLike(String text) {
        return ((root, query, criteriaBuilder) -> {
            Predicate p1 = criteriaBuilder.like(root.get("firstName"), "%" + text + "%");
            Predicate p2 = criteriaBuilder.like(root.get("lastName"), "%" + text + "%");
            return criteriaBuilder.and(p1, p2);
        });
    }

    public Specification<UserEntity> userStatusIs(StatusType statusType) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), statusType);
    }
    public Specification<UserEntity> userNameIs(String userName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("email"), userName);
    }
}
