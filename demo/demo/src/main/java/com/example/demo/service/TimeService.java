package com.example.demo.service;

import com.example.demo.dtos.TimeDTO;
import com.example.demo.dtos.TurmaDTO;
import com.example.demo.models.Esporte;
import com.example.demo.models.Estudante;
import com.example.demo.models.Time;
import com.example.demo.models.Turma;
import com.example.demo.models.Jogo;
import com.example.demo.repositories.EstudanteRepository;
import com.example.demo.repositories.JogoRepository;
import com.example.demo.repositories.TimeRepository;
import com.example.demo.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private JogoRepository jogoRepository;

    public TimeDTO save(TimeDTO dto) {
        Estudante capitao = null;
        Turma turma = null;

        if(dto.idCapitao() == null && dto.idTurma() == null) {
            Time time = new Time(dto, capitao, turma);
            return new TimeDTO(repository.save(time));
        }
        if(dto.idTurma() != null) {
            Optional<Turma> turmaOpt = turmaRepository.findById(dto.idTurma());
            turma = turmaOpt.orElse(null);
        }
        if(dto.idCapitao() != null) {
            Optional<Estudante> capitaoOp = estudanteRepository.findById(dto.idCapitao());
            capitao = capitaoOp.orElse(null);
        }
        Time time = new Time(dto, capitao, turma);

        return new TimeDTO(repository.save(time));
    }

    public TimeDTO saveStudent(Long id, List<Long> ids) {
        List<Estudante> estudantes = new ArrayList<>();
        if(ids == null) return null;
        for (Long aLong : ids) {
            Optional<Estudante> std = estudanteRepository.findById(aLong);
            std.ifPresent(estudantes::add);
        }
        if(estudantes.size() < 2 || estudantes.size() > 6) {
            return null;
        }
        Optional<Time> time = repository.findById(id);
        if(time.isEmpty()){
            return null;
        }
        Time newTime = time.get();
        newTime.setEstudantes(estudantes);
        return new TimeDTO(repository.save(newTime));
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
        Estudante capitao;
        Turma turma;
        Optional<Time> timeOpt = repository.findById(id);
        if(timeOpt.isEmpty()){
            return null;
        }
        Time newTime = timeOpt.get();
        newTime.setEsporte(Esporte.valueOf(dto.esporte().toLowerCase()));
        newTime.setNome(dto.nome());

        if(dto.idCapitao() == null && dto.idTurma() == null) return new TimeDTO(repository.save(newTime));
        if(dto.idTurma() != null) {
            Optional<Turma> turmaOpt = turmaRepository.findById(dto.idTurma());
            turma = turmaOpt.orElse(null);
            newTime.setTurma(turma);
        }
        if(dto.idCapitao() != null) {
            Optional<Estudante> capitaoOp = estudanteRepository.findById(dto.idCapitao());
            capitao = capitaoOp.orElse(null);
            newTime.setCapitao(capitao);
        }

        return new TimeDTO(repository.save(newTime));
    }

    public void delete(Long id) {
        Optional<Time> time = repository.findById(id);
        if(time.isEmpty()) {
            return;
        }
        List<Jogo> jogosTime1 = jogoRepository.findAllByTime1Is(time.get());
        List<Jogo> jogosTime2 = jogoRepository.findAllByTime2Is(time.get());
        jogoRepository.deleteAll(jogosTime1);
        jogoRepository.deleteAll(jogosTime2);
        repository.delete(time.get());
    }

}
