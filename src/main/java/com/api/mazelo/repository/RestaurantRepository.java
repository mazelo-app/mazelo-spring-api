package com.api.mazelo.repository;

import com.api.mazelo.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long>, JpaSpecificationExecutor<RestaurantEntity> {
}
