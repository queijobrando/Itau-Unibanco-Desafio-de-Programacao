package com.example.demo.service;

import com.example.demo.dto.EstatisticaDto;
import com.example.demo.dto.TransacaoDto;
import com.example.demo.model.Transacao;
import com.example.demo.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public Transacao salvarTransacao(TransacaoDto dto){
        if (dto.dataHora().isAfter(OffsetDateTime.now())){
            throw new IllegalArgumentException();
        }

        Transacao transacao = new Transacao(dto);
        transacaoRepository.salvar(transacao);
        return transacao;
    }

    public EstatisticaDto retornarEstatisticas(){
        List<Double> valores = transacaoRepository.retornarValores();
        if (valores.isEmpty()){
            return new EstatisticaDto(0, 0.0, 0.0, 0.0, 0.0);
        }
        DoubleSummaryStatistics stats = valores.stream().collect(Collectors.summarizingDouble(Double::doubleValue));

        return new EstatisticaDto(stats.getCount(), stats.getSum(), stats.getAverage(), stats.getMin(), stats.getMax());
    }


}
