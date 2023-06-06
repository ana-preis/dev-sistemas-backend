package com.incendiosflorestais.services;

import com.incendiosflorestais.dto.FireDTO;
import com.incendiosflorestais.models.Fire;
import com.incendiosflorestais.models.Identification;
import com.incendiosflorestais.models.ParkAddress;
import com.incendiosflorestais.models.User;
import com.incendiosflorestais.repositories.FireRepository;
import com.incendiosflorestais.repositories.ParkAddressRepository;
import com.incendiosflorestais.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.incendiosflorestais.dto.FireDTO.toFireDTOList;

@Service
public class FireService {

    @Autowired
    private FireRepository repository;

    @Autowired
    private ParkAddressRepository parkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public FireDTO save(FireDTO dto) {
        Optional<ParkAddress> parkOpt = parkRepository.findById(dto.parkID());
        Optional<User> responsible = userRepository.findById(dto.recordResponsibleID());
        Fire fire = new Fire(dto, responsible.get(), parkOpt.get());
        String corpoEmail = "Um novo incêndio no parque " + parkOpt.get().getParkName() + " foi salvo pelo usuário " + responsible.get().getUsername();
        this.emailService.enviarEmail(responsible.get().getEmail(), "Novo incêndio salvo", corpoEmail);
        return new FireDTO(repository.save(fire));
    }

    public FireDTO getByID(Long id) {
        Optional<Fire> fire = repository.findById(id);
        return new FireDTO(fire.get());
    }

    public List<FireDTO> search(Float latitude,
                                Float longitude,
                                LocalDateTime start,
                                String identification,
                                String city,
                                String state,
                                String parkName) {
        List<Fire> fires;
        Identification identificationValue;
        if(latitude == null && longitude == null &&
        start == null && identification == null &&
        city == null && state == null &&
        parkName == null) {
            return toFireDTOList(repository.findAll());
        }
        if(identification == null) {
            identificationValue = null;
        } else identificationValue = Identification.valueOf(identification.toUpperCase());
        fires = repository.findAllByParkAddress_CityOrParkAddress_StateOrParkAddress_ParkNameOrIdentificationOrLatitudeOrLongitudeOrStart(
                city, state, parkName, identificationValue, latitude, longitude, start
        );
        return toFireDTOList(fires);
    }

    public FireDTO update(Long id, FireDTO dto) {
        Optional<Fire> fireOpt = repository.findById(id);
        Identification identificationValue;
        if(dto.identification() == null) {
            identificationValue = null;
        } else identificationValue = Identification.valueOf(dto.identification().toUpperCase());
        Fire fire = fireOpt.get();
        fire.setLatitude(dto.latitude());
        fire.setLongitude(dto.longitude());
        fire.setStart(dto.start());
        fire.setEnd(dto.end());
        fire.setSavedAt(dto.savedAt());
        fire.setIdentification(identificationValue);
        fire.setSatelliteName(dto.satelliteName());

        if(dto.parkID() != null) {
            Optional<ParkAddress> parkOpt = parkRepository.findById(dto.parkID());
            parkOpt.ifPresent(fire::setParkAddress);
        }

        if(dto.recordResponsibleID() != null) {
            Optional<User> userOpt = userRepository.findById(dto.recordResponsibleID());
            userOpt.ifPresent(fire::setRecordResponsible);
        }

        return new FireDTO(fire);
    }

    public void delete(Long id) {
        Optional<Fire> fire = repository.findById(id);
        if(fire.isEmpty()) return;
        repository.delete(fire.get());
    }

    public void deleteParkInFireList(ParkAddress park) {
        List<Fire> fires = repository.findAllByParkAddress(park);
        if(fires.isEmpty()) return;
        for (Fire toUpdate : fires) {
            toUpdate.setParkAddress(null);
            repository.save(toUpdate);
        }
    }

    public void deleteUserInFireList(User user) {
        List<Fire> fires = repository.findAllByRecordResponsible(user);
        if(fires.isEmpty()) return;
        for (Fire toUpdate : fires) {
            toUpdate.setRecordResponsible(null);
            repository.save(toUpdate);
        }
    }
}
