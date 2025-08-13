package com.example.demo.repository;

import com.example.demo.model.Transacao;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransacaoRepository {

    private final List<Transacao> listaDeTransacoes = new ArrayList<>();

    public void salvar(Transacao transacao){
        listaDeTransacoes.add(transacao);
    }

    public List<Double> retornarValores(){
        OffsetDateTime umMinutoAtras = OffsetDateTime.now().minusSeconds(60);
        List<Double> valores = new ArrayList<>();

        listaDeTransacoes
                .forEach(transacao -> {
                    if (transacao.getCriadoEm().isAfter(umMinutoAtras)){
                        valores.add(transacao.getValor());
                    }
                });
        return valores;
    }

}
