package com.example.demo.service;

import com.example.demo.dtos.EstudanteDTO;
import com.example.demo.dtos.TimeDTO;
import com.example.demo.models.Estudante;
import com.example.demo.models.Time;
import com.example.demo.models.Turma;
import com.example.demo.repositories.EstudanteRepository;
import com.example.demo.repositories.TimeRepository;
import com.example.demo.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TimeService {

    @Autowired
    private TimeRepository repository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private EstudanteRepository estudanteRepository;

    public TimeDTO save(TimeDTO dto) {
        Optional<Estudante> capitaoOp = estudanteRepository.findById(dto.idCapitao());
        if(capitaoOp.isEmpty()) {
            return null;
        }
        Optional<Turma> turmaOpt = turmaRepository.findById(dto.idTurma());
        if(turmaOpt.isEmpty()) {
            return null;
        }
        List<Estudante> estudantes = transform(dto);
        Time time = new Time(dto, capitaoOp.get(), estudantes, turmaOpt.get());

        repository.save(time);
        TimeDTO newDto = new TimeDTO(time);
        return newDto;
    }


    public List<Estudante> transform(TimeDTO dto) {
        List<Estudante> = estudanteRepository.findByInventoryIdIn(dto.estudanteIds())
    };

    public static List<Estudante> toEstudanteList(List<EstudanteDTO> dtos, Turma) {
        return dtos.stream()
                .map(dto -> new Estudante(dto))
                .collect(Collectors.toList());
    }
}
}
