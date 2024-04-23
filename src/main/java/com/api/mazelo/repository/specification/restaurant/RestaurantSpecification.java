package com.api.mazelo.repository.specification.restaurant;


import com.api.mazelo.entity.RestaurantEntity;
import com.api.mazelo.type.StatusType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class RestaurantSpecification {
    public Specification<RestaurantEntity> IdIs(Long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }
    public Specification<RestaurantEntity> userNameIs(String userName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("email"), userName);
    }

    public Specification<RestaurantEntity> restaurantNameLike(String text) {
        return ((root, query, criteriaBuilder) -> {
            Predicate p1 = criteriaBuilder.like(root.get("name"), "%" + text + "%");
            return criteriaBuilder.and(p1);
        });
    }

    public Specification<RestaurantEntity> statusIs(StatusType statusType) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), statusType);
    }
}
