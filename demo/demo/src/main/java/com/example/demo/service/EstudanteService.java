package com.example.demo.service;

import com.example.demo.dtos.EstudanteDTO;
import com.example.demo.models.Estudante;
import com.example.demo.models.Turma;
import com.example.demo.repositories.EstudanteRepository;
import com.example.demo.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.dtos.EstudanteDTO.toEstudanteDTOList;

@Service
public class EstudanteService {

    @Autowired
    private EstudanteRepository repository;

    @Autowired
    private TurmaRepository turmaRepository;

    public EstudanteDTO save(EstudanteDTO dto) {
        Estudante estudante = new Estudante(dto);
        return new EstudanteDTO(repository.save(estudante));
    }

    public EstudanteDTO getByID(Long id) {
        Optional<Estudante> estudante = repository.findById(id);
        if (estudante.isEmpty()) {
            return null;
        }
        Long turmaId;
        if(estudante.get().getTurma() == null) {
            turmaId = null;
        } else {
            turmaId = estudante.get().getTurma().getId();
        }
        EstudanteDTO dto = new EstudanteDTO(estudante.get(), turmaId);
        return dto;
    }

    public List<EstudanteDTO> getAll() {
        List<Estudante> estudantes = repository.findAll();
        if(estudantes.isEmpty()){
            return new ArrayList<>();
        }
        return toEstudanteDTOList(estudantes);
    }


    public EstudanteDTO update(EstudanteDTO dto, Long id){
        Optional<Estudante> estudante = repository.findById(id);
        if(estudante.isEmpty()){
            return null;
        }
        Optional<Turma> turma = turmaRepository.findById(dto.idTurma());
        if(turma.isEmpty()) {
            return null;
        }
        Estudante novoEstudante = estudante.get();
        novoEstudante.setMatricula(dto.matricula());
        novoEstudante.setTurma(turma.get());
        Estudante salvo = repository.save(novoEstudante);
        return new EstudanteDTO(salvo);
    }

    public void delete(Long id) {
        Optional<Estudante> estudante = repository.findById(id);
        if (estudante.isEmpty()) {
            return;
        }
        repository.delete(estudante.get());
    }
}
