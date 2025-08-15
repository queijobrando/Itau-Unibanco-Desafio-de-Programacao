package com.example.demo.service;

import com.example.demo.dto.EstatisticaDto;
import com.example.demo.dto.TransacaoDto;
import com.example.demo.model.Transacao;
import com.example.demo.repository.TransacaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public Transacao salvarTransacao(TransacaoDto dto){
        if (dto.dataHora().isAfter(OffsetDateTime.now())){
            log.error("Data/Hora da transação maior que a data atual");
            throw new IllegalArgumentException();
        }

        Transacao transacao = new Transacao(dto);
        transacaoRepository.salvar(transacao);
        log.info("Transação salva com sucesso");
        return transacao;
    }

    public void deletarTransacoes(){
        transacaoRepository.deletarTudo();
        log.info("Transações deletadas com sucesso");
    }

    public EstatisticaDto retornarEstatisticas(String segundos){
        List<Double> valores = transacaoRepository.retornarValores(segundos);
        if (valores.isEmpty()){
            log.info("Estatisticas calculadas com sucesso");
            return new EstatisticaDto(0, 0.0, 0.0, 0.0, 0.0);
        }
        DoubleSummaryStatistics stats = valores.stream().collect(Collectors.summarizingDouble(Double::doubleValue));

        log.info("Estatisticas calculadas com sucesso");
        return new EstatisticaDto(stats.getCount(), stats.getSum(), stats.getAverage(), stats.getMin(), stats.getMax());
    }


}
