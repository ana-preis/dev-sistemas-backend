package com.incendiosflorestais.services;

import com.incendiosflorestais.dto.ParkAddressDTO;
import com.incendiosflorestais.models.ParkAddress;
import com.incendiosflorestais.repositories.FireRepository;
import com.incendiosflorestais.repositories.ParkAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.incendiosflorestais.dto.ParkAddressDTO.toParkAddressDTOList;

@Service
public class ParkAddressService {

    @Autowired
    private ParkAddressRepository repository;

    @Autowired
    private FireService fireService;

    public ParkAddressDTO save(ParkAddressDTO dto) {
        ParkAddress park = new ParkAddress(dto);
        return new ParkAddressDTO(repository.save(park));
    }

    public ParkAddressDTO getByID(Long id) {
        Optional<ParkAddress> park = repository.findById(id);
        return new ParkAddressDTO(park.get());
    }

    public List<ParkAddressDTO> search(String city, String state, String name) {
        List<ParkAddress> parks;
        if(city == null && state == null && name == null){
            parks = repository.findAll();
        } else {
            parks = repository.findAllByCityOrStateOrParkName(city, state, name);
        }
        return toParkAddressDTOList(parks);
    }

    public ParkAddressDTO update(Long id, ParkAddressDTO dto) {
        Optional<ParkAddress> parkOpt = repository.findById(id);
        ParkAddress park = parkOpt.get();
        park.setCity(dto.city());
        park.setState(dto.state());
        park.setParkName(dto.parkName());
        park.setCountry(dto.country());
        return new ParkAddressDTO(repository.save(park));
    }

    public void delete(Long id) {
        Optional<ParkAddress> parkOpt = repository.findById(id);
        if(parkOpt.isEmpty()) return;
        fireService.deleteParkInFireList(parkOpt.get());
        repository.delete(parkOpt.get());
    }
}
