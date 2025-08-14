package com.example.demo.repository;

import com.example.demo.dto.TransacaoDto;
import com.example.demo.model.Transacao;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransacaoRepositoryTest {

    @Test
    void shouldSaveTransaction(){
        TransacaoRepository repository = new TransacaoRepository();
        Transacao transacao = new Transacao(new TransacaoDto(200.00, OffsetDateTime.now()));

        repository.salvar(transacao);
        List<Double> valores = repository.retornarValores();
        assertEquals(1, valores.size());
        assertTrue(valores.contains(200.00));
    }

}