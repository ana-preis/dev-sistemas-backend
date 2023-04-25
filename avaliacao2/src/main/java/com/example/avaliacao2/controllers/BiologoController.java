package com.example.avaliacao2.controllers;

import com.example.avaliacao2.dtos.BiologoDTO;
import com.example.avaliacao2.models.Biologo;
import com.example.avaliacao2.repositories.BiologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.avaliacao2.dtos.BiologoDTO.toBiologoDTOList;

@RestController
@RequestMapping("/biologos")
public class BiologoController {

    @Autowired
    private BiologoRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<BiologoDTO> getByID(@PathVariable Integer id) {
        Optional<Biologo> biologo = repository.findById(id);
        if(biologo.isPresent()) {
            BiologoDTO dto = new BiologoDTO(biologo.get());
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("")
    public ResponseEntity<List<BiologoDTO>> search(@RequestParam (defaultValue = "") Integer codigo,
                                             @RequestParam (defaultValue = "") String uni) {
        ArrayList<Biologo> biologo = repository.findAllByCodigoAndUniversidade(codigo, uni);
        if(biologo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<BiologoDTO> dtos = toBiologoDTOList(biologo);
        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }


}
