package com.example.demo.model;

import com.example.demo.dto.TransacaoDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "id")
public class Transacao {

    private final UUID id;
    private final Double valor;
    private final OffsetDateTime dataHora;
    private final OffsetDateTime criadoEm;

    public Transacao(TransacaoDto dto){
        this.valor = dto.valor();
        this.dataHora = dto.dataHora();
        this.id = UUID.randomUUID();
        this.criadoEm = OffsetDateTime.now();
    }


}
