package br.com.jcr.hrbitcoinpredict.services;

import ai.djl.translate.TranslateException;
import br.com.jcr.hrbitcoinpredict.entities.BitcoinSummary;
import br.com.jcr.hrbitcoinpredict.entities.FullBitcoin;
import br.com.jcr.hrbitcoinpredict.entities.SimpleBitcoin;
import br.com.jcr.hrbitcoinpredict.exceptions.BitcoinBrasilException;
import br.com.jcr.hrbitcoinpredict.predicts.BitcoinPredict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class BitcoinService {

    @Autowired
    private BitcoinPredict bitcoinPredict;

    @Autowired
    private WebClient webClient;

    public FullBitcoin getBitcoinPredictBySimpleBitcoin(SimpleBitcoin simpleBitcoin) throws TranslateException {

        return FullBitcoin.builder()
                .simpleBitcoin(simpleBitcoin)
                .prediction(bitcoinPredict.predict(simpleBitcoin.getInputPredict()))
                .build();
    }


    public FullBitcoin getBitcoinPredictByDate(@NonNull LocalDate date) throws TranslateException {
        Mono<BitcoinSummary> bitcoinSummaryMono = webClient.get()
                .uri(
                        "/dailySummary/BTC/{day}/{month}/{year}",
                        date.getDayOfMonth(),
                        date.getMonthValue(),
                        date.getYear()
                )
                .retrieve()
                .bodyToMono(BitcoinSummary.class)
                .onErrorMap( throwable -> {
                    return new BitcoinBrasilException("Erro na API Brasil Bitcoin tente mais tarde!");
                });

        BitcoinSummary bitcoinSummary = bitcoinSummaryMono.block();
        assert bitcoinSummary != null;
        SimpleBitcoin simpleBitcoin = bitcoinSummary.toSimpleBitcoin();

        return FullBitcoin.builder()
                .simpleBitcoin(simpleBitcoin)
                .prediction(bitcoinPredict.predict(simpleBitcoin.getInputPredict()))
                .date(bitcoinSummary.getDate())
                .build();
    }
}
