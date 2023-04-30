package com.incendiosflorestais.repositories;

import com.incendiosflorestais.models.Fire;
import com.incendiosflorestais.models.Identification;
import com.incendiosflorestais.models.ParkAddress;
import com.incendiosflorestais.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FireRepository extends JpaRepository<Fire, Long> {
    List<Fire> findAllByParkAddress_CityOrParkAddress_StateOrParkAddress_ParkNameOrIdentificationOrLatitudeOrLongitudeOrStart(
            String city, String state, String parkName, Identification identification, Float latitude, Float longitude, LocalDateTime start
    );
    List<Fire> findAllByParkAddress(ParkAddress parkAddress);
    List<Fire> findAllByRecordResponsible(User recordResponsible);
}
