package com.example.demo.service;

import com.example.demo.dtos.TimeDTO;
import com.example.demo.dtos.TurmaDTO;
import com.example.demo.models.Esporte;
import com.example.demo.models.Estudante;
import com.example.demo.models.Time;
import com.example.demo.models.Turma;
import com.example.demo.repositories.EstudanteRepository;
import com.example.demo.repositories.TimeRepository;
import com.example.demo.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.demo.dtos.TimeDTO.toTimeDTOList;

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
        Time time = new Time(dto, capitaoOp.get(), turmaOpt.get());

        repository.save(time);
        TimeDTO newDto = new TimeDTO(time);
        return newDto;
    }

    public TimeDTO getByID(Long id) {
        Optional<Time> time = repository.findById(id);
        if(time.isEmpty()) {
            return null;
        }
        TimeDTO dto = new TimeDTO(time.get());
        return dto;
    }

    public List<TimeDTO> search(Long idTurma) {
        List<Time> times;
        if(idTurma == null) {
            times = repository.findAll();
        } else {
            times = repository.findAllByTurma_Id(idTurma);
        }
        return toTimeDTOList(times);
    }

    public TimeDTO update(Long id, TimeDTO dto) {
        Optional<Time> timeOpt = repository.findById(id);
        if(timeOpt.isEmpty()){
            return null;
        }
        Optional<Estudante> capitaoOp = estudanteRepository.findById(dto.idCapitao());
        if(capitaoOp.isEmpty()) {
            return null;
        }
        Optional<Turma> turmaOpt = turmaRepository.findById(dto.idTurma());
        if(turmaOpt.isEmpty()) {
            return null;
        }
        Time newTime = timeOpt.get();
        newTime.setCapitao(capitaoOp.get());
        newTime.setEsporte(Esporte.valueOf(dto.esporte()));
        newTime.setNome(dto.nome());
        newTime.setTurma(turmaOpt.get());
        TimeDTO newDto = new TimeDTO(repository.save(newTime));
        return newDto;
    }

    public void delete(Long id) {
        Optional<Time> time = repository.findById(id);
        if(time.isEmpty()) {
            return;
        }
        repository.delete(time.get());
    }

}
