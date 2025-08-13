package com.example.demo.controller;

import com.example.demo.dto.EstatisticaDto;
import com.example.demo.dto.TransacaoDto;
import com.example.demo.model.Transacao;
import com.example.demo.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class TransacaoController implements GenericController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

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

    @GetMapping("/estatistica")
    public ResponseEntity<EstatisticaDto> retornarEstatisticas(){
        return ResponseEntity.ok(transacaoService.retornarEstatisticas());
    }

}
