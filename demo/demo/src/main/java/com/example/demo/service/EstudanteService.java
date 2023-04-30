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
        return new EstudanteDTO(estudante.get());
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
        Estudante novoEstudante = estudante.get();
        novoEstudante.setNome(dto.nome());
        novoEstudante.setSenha(dto.senha());
        novoEstudante.setIs_admin(dto.is_admin());
        novoEstudante.setMatricula(dto.matricula());
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
