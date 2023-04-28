package com.example.demo.service;

import com.example.demo.dtos.TurmaDTO;
import com.example.demo.models.Turma;
import com.example.demo.models.Turno;
import com.example.demo.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.dtos.TurmaDTO.toTurmaDTOList;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository repository;

    public TurmaDTO save(TurmaDTO dto) {
        //VALIDA TURNO
        Turma turma = new Turma(dto);
        return new TurmaDTO(repository.save(turma));
    }

    public TurmaDTO getByID(Long id) {
        Optional<Turma> turma = repository.findById(id);
        if(turma.isEmpty()) {
            return null;
        }
       return new TurmaDTO(turma.get());
    }

    public List<TurmaDTO> getAll() {
        List<Turma> turmas = repository.findAll();
        if(turmas.isEmpty()) {
            return new ArrayList<>();
        }
        return toTurmaDTOList(turmas);
    }

    public TurmaDTO update(Long id, TurmaDTO dto) {
        Optional<Turma> turma = repository.findById(id);
        if(turma.isEmpty()){
            return null;
        }
        Turma novaTurma = turma.get();
        novaTurma.setNome(dto.nome());
        novaTurma.setTurno(Turno.valueOf(dto.turno()));
        novaTurma.setSerie(dto.serie());
        return new TurmaDTO(repository.save(novaTurma));
    }

    public void delete(Long id) {
        Optional<Turma> turma = repository.findById(id);
        if (turma.isEmpty()) {
            return;
        }
        repository.delete(turma.get());
    }
}
