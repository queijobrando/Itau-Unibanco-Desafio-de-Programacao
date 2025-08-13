package com.example.demo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "id")
public class Transacao {

    private final UUID id;
    private final Double valor;
    private final LocalDateTime dataHora;
    private final LocalDateTime criadoEm;

    public Transacao(Double valor, LocalDateTime dataHora){
        this.valor = valor;
        this.dataHora = dataHora;
        this.id = UUID.randomUUID();
        this.criadoEm = LocalDateTime.now();
    }


}
