package com.example.demo.repository;

import com.example.demo.model.Transacao;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TransacaoRepository {

    private final List<Transacao> listaDeTransacoes = new ArrayList<>();

    public void salvar(Transacao transacao){
        listaDeTransacoes.add(transacao);
    }

    public List<Transacao> findAll(){
        return listaDeTransacoes;
    }

    public Optional<Transacao> findById(UUID id){
        return listaDeTransacoes.stream()
                .filter(transacao -> transacao.getId().equals(id))
                .findFirst();
    }

    public void deletarTudo(){
        listaDeTransacoes.clear();
    }

    public List<Double> retornarValores(String segundos){
        OffsetDateTime minutosAtras = OffsetDateTime.now().minusSeconds(Integer.parseInt(segundos));
        List<Double> valores = new ArrayList<>();

        listaDeTransacoes
                .forEach(transacao -> {
                    if (transacao.getCriadoEm().isAfter(minutosAtras)){
                        valores.add(transacao.getValor());
                    }
                });
        return valores;
    }

}
