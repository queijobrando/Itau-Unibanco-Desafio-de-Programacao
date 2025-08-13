package com.example.demo.repository;

import com.example.demo.dto.EstatisticaDto;
import com.example.demo.model.Transacao;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class TransacaoRepository {

    private final List<Transacao> listaDeTransacoes = new ArrayList<>();

    public void salvar(Transacao transacao){
        listaDeTransacoes.add(transacao);
    }

    public List<Transacao> listarTransacoes(){
        return new ArrayList<>(listaDeTransacoes);
    }

    public EstatisticaDto retornarEstatisticas(){
        LocalDateTime umMinutoAtras = LocalDateTime.now().minusSeconds(60);
        List<Double> valores = new ArrayList<>();

        listaDeTransacoes.stream()
                .forEach(transacao -> {
                    if (transacao.getCriadoEm().isAfter(umMinutoAtras)){
                        valores.add(transacao.getValor());
                    }
                });

        return calcularEstatisticas(valores);
    }

    public EstatisticaDto calcularEstatisticas(List<Double> valores){
        int count = valores.size();
        double sum = valores.stream().mapToDouble(Double::doubleValue).sum();
        double avg = sum * count;


        EstatisticaDto estatistica = new EstatisticaDto(valores.size(), soma, )
    }

}
