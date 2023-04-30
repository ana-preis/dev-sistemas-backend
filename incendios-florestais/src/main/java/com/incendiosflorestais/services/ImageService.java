package com.incendiosflorestais.services;

import com.incendiosflorestais.dto.ImageDTO;
import com.incendiosflorestais.models.Fire;
import com.incendiosflorestais.models.Identification;
import com.incendiosflorestais.models.Image;
import com.incendiosflorestais.repositories.FireRepository;
import com.incendiosflorestais.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.incendiosflorestais.dto.ImageDTO.toImageDTOList;

@Service
public class ImageService {

    @Autowired
    private ImageRepository repository;

    @Autowired
    private FireRepository fireRepository;


    public ImageDTO save(ImageDTO dto) {
        Optional<Fire> fire = fireRepository.findById(dto.fireID());
        if(fire.isEmpty()) return null;
        Image img = new Image(dto, fire.get());
        return new ImageDTO(repository.save(img));
    }

    public ImageDTO getByID(Long id) {
        Optional<Image> img = repository.findById(id);
        if(img.isEmpty()) return null;
        return new ImageDTO(img.get());
    }

    public List<ImageDTO> search(String fileName, LocalDateTime date) {
        List<Image> imgs;
        if(fileName == null && date == null){
            imgs = repository.findAll();
        } else {
            imgs = repository.findAllByFileNameOrDate(fileName, date);
        }
        return toImageDTOList(imgs);
    }

    public ImageDTO update(Long id, ImageDTO dto) {
        Optional<Image> imgOpt = repository.findById(id);
        Identification identificationValue;
        if(dto.type() == null) {
            identificationValue = null;
        } else identificationValue = Identification.valueOf(dto.type().toUpperCase());
        if(imgOpt.isEmpty()) return null;
        Image img = imgOpt.get();
        img.setFileName(dto.fileName());
        img.setType(identificationValue);
        img.setDate(dto.date());
        return new ImageDTO(repository.save(img));
    }

    public void delete(Long id) {
        Optional<Image> imgOpt = repository.findById(id);
        if(imgOpt.isEmpty()) return;
        repository.delete(imgOpt.get());
    }
}
