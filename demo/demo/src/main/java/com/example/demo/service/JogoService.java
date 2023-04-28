package com.example.demo.service;

import com.example.demo.dtos.JogoDTO;
import com.example.demo.models.Esporte;
import com.example.demo.models.Jogo;
import com.example.demo.models.Time;
import com.example.demo.repositories.JogoRepository;
import com.example.demo.repositories.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return new JogoDTO(repository.save(newJogo), time1.get().getId(), time2.get().getId());
    }

    public JogoDTO getByID(Long id) {
        Optional<Jogo> jogo = repository.findById(id);
        if(jogo.isEmpty()){
            return null;
        }
        Long time1Id;
        Long time2Id;
        if(jogo.get().getTime1() == null) {
            time1Id = null;
        } else {
            time1Id = jogo.get().getTime1().getId();
        }
        if(jogo.get().getTime2() == null) {
            time2Id = null;
        } else {
            time2Id = jogo.get().getTime2().getId();
        }
        return new JogoDTO(jogo.get(), time1Id, time2Id);
    }

    public List<JogoDTO> search(String esporte) {
        List<Jogo> jogos;
        if(esporte == "") {
            jogos = repository.findAll();
        } else {
            Esporte esp = Esporte.valueOf(esporte);
            jogos = repository.findAllByEsporte(esp);
        }
        List<JogoDTO> dtos = toJogoDTOList(jogos);
        return dtos;
    }

    public JogoDTO update(Long id, JogoDTO dto) {
        Optional<Jogo> jogo = repository.findById(id);
        if(jogo.isEmpty()) {
            return null;
        }
        Optional<Time> time1 = timeRepository.findById(dto.idTime1());
        if(time1.isEmpty()) {
            return null;
        }
        Optional<Time> time2 = timeRepository.findById(dto.idTime2());
        if(time2.isEmpty()){
            return null;
        }
        Jogo newJogo = jogo.get();
        newJogo.setData(dto.data());
        newJogo.setEsporte(Esporte.valueOf(dto.esporte()));
        newJogo.setTime1(time1.get());
        newJogo.setTime2(time2.get());
        return new JogoDTO(repository.save(newJogo), time1.get().getId(), time2.get().getId());
    }

    public void delete(Long id) {
        Optional<Jogo> jogo = repository.findById(id);
        if(jogo.isEmpty()) {
            return;
        }
        repository.delete(jogo.get());
    }

}
