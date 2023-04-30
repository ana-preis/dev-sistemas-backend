package com.example.demo.service;

import com.example.demo.dtos.JogoDTO;
import com.example.demo.models.Esporte;
import com.example.demo.models.Jogo;
import com.example.demo.models.Time;
import com.example.demo.repositories.JogoRepository;
import com.example.demo.repositories.TimeRepository;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.dtos.JogoDTO.toJogoDTOList;

@Service
public class JogoService {

    @Autowired
    private JogoRepository repository;

    @Autowired
    private TimeRepository timeRepository;


    public JogoDTO save(JogoDTO dto) {
        Optional<Time> time1 = timeRepository.findById(dto.idTime1());
        if(time1.isEmpty()) {
            return null;
        }
        Optional<Time> time2 = timeRepository.findById(dto.idTime2());
        if(time2.isEmpty()){
            return null;
        }
        Jogo newJogo = new Jogo(dto, time1.get(), time2.get());
        return new JogoDTO(repository.save(newJogo));
    }

    public JogoDTO getByID(Long id) {
        Optional<Jogo> jogo = repository.findById(id);
        if(jogo.isEmpty()){
            return null;
        }
        return new JogoDTO(jogo.get());
    }

    public List<JogoDTO> search(String esporte) {
        List<Jogo> jogos = repository.findAll();
        if(esporte == null) {
            if(jogos.size() == 0) {
                return new ArrayList<>();
            }
        } else {
            Esporte esp = Esporte.valueOf(esporte.toLowerCase());
            jogos = repository.findAllByEsporte(esp);
        }
        return toJogoDTOList(jogos);
    }

    public JogoDTO update(Long id, JogoDTO dto) {
        Time time1;
        Time time2;
        Optional<Jogo> jogo = repository.findById(id);
        if(jogo.isEmpty()) {
            return null;
        }
        Jogo newJogo = jogo.get();
        newJogo.setData(dto.data());
        newJogo.setEsporte(Esporte.valueOf(dto.esporte().toLowerCase()));

        if(dto.idTime1() == null && dto.idTime2() == null) return new JogoDTO(repository.save(newJogo));

        if(dto.idTime1() != null) {
            Optional<Time> time1Opt = timeRepository.findById(dto.idTime1());
            time1 = time1Opt.orElse(null);
            newJogo.setTime1(time1);
        }

        if(dto.idTime2() != null) {
            Optional<Time> time2Opt = timeRepository.findById(dto.idTime2());
            time2 = time2Opt.orElse(null);
            newJogo.setTime2(time2);
        }
        return new JogoDTO(repository.save(newJogo));
    }

    public JogoDTO updatePlacar(Long id, JogoDTO dto) {
        if(dto.placar_time_1() == null || dto.placat_time_2() == null) return null;
        Optional<Jogo> jogoOpt = repository.findById(id);
        if(jogoOpt.isEmpty()) return null;
        Jogo jogo = jogoOpt.get();
        jogo.setPlacar_time1(dto.placar_time_1());
        jogo.setPlacar_time2(dto.placat_time_2());
        return new JogoDTO(repository.save(jogo));
    }

    public void delete(Long id) {
        Optional<Jogo> jogo = repository.findById(id);
        if(jogo.isEmpty()) {
            return;
        }
        repository.delete(jogo.get());
    }

}
