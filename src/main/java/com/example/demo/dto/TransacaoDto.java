package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TransacaoDto(
        @NotNull
        @Min(0)
        Double valor,
        @NotNull
        LocalDateTime dataHora
) {
}
