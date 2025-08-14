package com.example.demo.service;

import com.example.demo.dto.TransacaoDto;
import com.example.demo.repository.TransacaoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class TransacaoServiceTest {

    @Test
    void shouldThrowIllegalArgumentExceptionWhenDateTimeIsAfterNow() {

        TransacaoRepository repository = mock(TransacaoRepository.class);
        TransacaoService service = new TransacaoService(repository);

        OffsetDateTime dataFutura = OffsetDateTime.now().plusDays(1);
        TransacaoDto dto = new TransacaoDto(200.00, dataFutura);

        assertThrows(IllegalArgumentException.class, () -> {
            service.salvarTransacao(dto);
        });

        Mockito.verify(repository, Mockito.never()).salvar(Mockito.any());
    }
}