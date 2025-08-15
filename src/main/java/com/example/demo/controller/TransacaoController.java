package com.example.demo.controller;

import com.example.demo.dto.EstatisticaDto;
import com.example.demo.dto.TransacaoDto;
import com.example.demo.model.Transacao;
import com.example.demo.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class TransacaoController implements GenericController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @Operation(summary = "Enviar Transação", description = "Endpoint responsável por receber e salvar transações", tags = "Gerenciar Transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação enviada com sucesso"),
            @ApiResponse(responseCode = "422", description = "A transação não foi aceita por qualquer motivo"),
            @ApiResponse(responseCode = "400", description = "A API não compreendeu a requisição do cliente")
    })
    @PostMapping("/transacao")
    public ResponseEntity<Void> enviarTransacao(@Valid @RequestBody TransacaoDto dto){
        try {
            Transacao transacao = transacaoService.salvarTransacao(dto);

            URI location = generateHeaderLocation(transacao.getId());

            return ResponseEntity.created(location).build();
        } catch (Exception e){
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @Operation(summary = "Calcular Estatisticas", description = "Endpoint responsável por retornar  estatísticas das transações que aconteceram nos últimos 60 segundos", tags = "Estatísticas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados calculados e exibidos com sucesso")
    })
    @GetMapping("/estatistica")
    public ResponseEntity<EstatisticaDto> retornarEstatisticas(@RequestParam(defaultValue = "60") String segundos){
        return ResponseEntity.ok(transacaoService.retornarEstatisticas(segundos));
    }

    @Operation(summary = "Deletar Transações", description = "Endpoint responsável por deletar todas as transações salvas", tags = "Gerenciar Transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todas as transações foram apagadas com sucesso"),
    })
    @DeleteMapping("/transacao")
    public ResponseEntity<Void> deletarTransacoes(){
        transacaoService.deletarTransacoes();
        return ResponseEntity.ok().build();
    }

}
