package com.incendiosflorestais.repositories;

import com.incendiosflorestais.models.ParkAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkAddressRepository extends JpaRepository<ParkAddress, Long> {
    List<ParkAddress> findAllByCityOrStateOrParkName(String city, String state, String parkName);
}
