package com.example.demo.repository;

import com.example.demo.dto.TransacaoDto;
import com.example.demo.model.Transacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransacaoRepositoryTest {

    private TransacaoRepository repository;

    @BeforeEach
    void setuUp(){
        repository = new TransacaoRepository();
    }

    @Test
    void shouldSaveTransaction(){
        Transacao transacao = new Transacao(new TransacaoDto(200.00, OffsetDateTime.now()));
        repository.salvar(transacao);

        List<Transacao> lista = repository.findAll();

        assertEquals(1, lista.size());
        assertTrue(lista.contains(transacao));
    }

    @Test
    void shouldFindTransactionById(){
        Transacao transacao = new Transacao(new TransacaoDto(300.00, OffsetDateTime.now()));
        repository.salvar(transacao);

        assertTrue(repository.findById(transacao.getId()).isPresent());
        assertEquals(transacao, repository.findById(transacao.getId()).get());
    }

    @Test
    void shouldntFindTransactionById(){
        UUID id = UUID.randomUUID();

        assertFalse(repository.findById(id).isPresent());
        assertEquals(Optional.empty(), repository.findById(id));
    }

    @Test
    void should60Minutes(){
        List<Double> valores = repository.retornarValores("60");

        assertTrue(valores.isEmpty());
    }

}