package br.com.jcr.hrbitcoinpredict.resources;

import ai.djl.translate.TranslateException;
import br.com.jcr.hrbitcoinpredict.entities.FullBitcoin;
import br.com.jcr.hrbitcoinpredict.entities.ResultData;
import br.com.jcr.hrbitcoinpredict.entities.SimpleBitcoinPredict;
import br.com.jcr.hrbitcoinpredict.services.BitcoinService;
import br.com.jcr.hrbitcoinpredict.utils.models.LogPattern;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping(value = "/bitcoin")
public class BitcoinResource {
    @Autowired
    private BitcoinService bitcoinService;

    private static final String successMessage =  "Predição realizada com sucesso!";

    @Operation(summary = "Performs the prediction through the values of OPEN, HIGH and LOW")
    @PostMapping
    public ResponseEntity<ResultData<FullBitcoin>> getBitcoinPredictBySimpleBitcoinPredict(@RequestBody SimpleBitcoinPredict simpleBitcoinPredict) throws TranslateException {
        log.info(String.format(LogPattern.builder()
                .classLogging("BitcoinResource")
                .methodLogging("getBitcoinPredictBySimpleBitcoin")
                .data(simpleBitcoinPredict.toString())
                .dateTime(LocalDateTime.now())
                .build().toString()
        ));
        ResultData resultData;

        FullBitcoin predict = bitcoinService.getBitcoinPredictBySimpleBitcoin(simpleBitcoinPredict.toSimpleBitcoin());

        resultData = new ResultData(
                HttpStatus.OK.value(),
                successMessage,
                predict
        );
        log.info(String.format(LogPattern.builder()
                .classLogging("BitcoinResource")
                .methodLogging("getBitcoinPredictBySimpleBitcoin")
                .data(predict.toString())
                .message(successMessage)
                .status(HttpStatus.OK.value())
                .dateTime(LocalDateTime.now())
                .build().toString()
        ));
        return ResponseEntity.ok(resultData);

    }

    @Operation(summary = "Performs actual date prediction")
    @GetMapping
    public ResponseEntity<ResultData<FullBitcoin>> getBitcoinPredictByNow() throws TranslateException {
        log.info(String.format(LogPattern.builder()
                .classLogging("BitcoinResource")
                .methodLogging("getBitcoinPredictByNow")
                .dateTime(LocalDateTime.now())
                .build().toString()
        ));
        ResultData resultData;
        LocalDate date = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
        FullBitcoin fullBitcoin = bitcoinService.getBitcoinPredictByDate(date);
        resultData = new ResultData(
                HttpStatus.OK.value(),
                successMessage,
                fullBitcoin
        );
        log.info(String.format(LogPattern.builder()
                .classLogging("BitcoinResource")
                .methodLogging("getBitcoinPredictByNow")
                .data(fullBitcoin.toString())
                .message(successMessage)
                .status(HttpStatus.OK.value())
                .dateTime(LocalDateTime.now())
                .build().toString()
        ));
        return ResponseEntity.ok(resultData);
    }

    @Operation(summary = "Perform prediction by date")
    @GetMapping(value = "/{day}/{month}/{year}")
    public ResponseEntity<ResultData<FullBitcoin>> getBitcoinPredictByDate(
            @PathVariable String day,
            @PathVariable String month,
            @PathVariable String year
    ) throws TranslateException {
        log.info(String.format(LogPattern.builder()
                .classLogging("BitcoinResource")
                .methodLogging("getBitcoinPredictByDate")
                .data(String.format("day=%s month=%s year=%s", day, month, year))
                .dateTime(LocalDateTime.now())
                .build().toString()
        ));
        ResultData resultData;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String date = String.format("%s/%s/%s", day, month, year);
        ;
        LocalDate localDate = LocalDate.parse(date, formatter);
        FullBitcoin fullBitcoin = bitcoinService.getBitcoinPredictByDate(localDate);
        resultData = new ResultData(
                HttpStatus.OK.value(),
                "Predição realizada com sucesso! ",
                fullBitcoin
        );
        log.info(String.format(LogPattern.builder()
                .classLogging("BitcoinResource")
                .methodLogging("getBitcoinPredictByNow")
                .data(fullBitcoin.toString())
                .message(successMessage)
                .status(HttpStatus.OK.value())
                .dateTime(LocalDateTime.now())
                .build().toString()
        ));
        return ResponseEntity.ok(resultData);


    }
}
