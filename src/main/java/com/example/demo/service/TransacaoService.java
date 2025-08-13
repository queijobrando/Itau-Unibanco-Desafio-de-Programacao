package com.example.demo.service;

import com.example.demo.dto.TransacaoDto;
import com.example.demo.model.Transacao;
import com.example.demo.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public void salvarTransacao(TransacaoDto dto){
        if (dto.dataHora().isAfter(LocalDateTime.now())){
            throw new IllegalArgumentException();
        }

        Transacao transacao = new Transacao(dto.valor(), dto.dataHora());
        transacaoRepository.salvar(transacao);


    }


}
