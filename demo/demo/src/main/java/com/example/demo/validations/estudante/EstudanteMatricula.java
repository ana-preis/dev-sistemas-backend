package com.example.demo.validations.estudante;

import com.example.demo.dtos.EstudanteDTO;
import com.example.demo.exeptions.ValidacaoException;
import com.example.demo.validations.Validation;

public class EstudanteMatricula implements Validation<EstudanteDTO> {

    @Override
    public void validar(EstudanteDTO dto) {
        if(dto.matricula().isEmpty()){
            throw new ValidacaoException(
                    "Matrícula do estudante não pode ficar vazia"
            );
        }

    }
}
