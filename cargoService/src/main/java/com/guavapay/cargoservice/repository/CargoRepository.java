package com.guavapay.cargoservice.repository;

import com.guavapay.cargoservice.model.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

    Cargo findByOrderId(long orderId);
    List<Cargo> findAllByCourierId(long orderId);
}