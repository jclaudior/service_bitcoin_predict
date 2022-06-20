package br.com.jcr.hrbitcoinpredict.builders;

import br.com.jcr.hrbitcoinpredict.entities.FullBitcoin;
import br.com.jcr.hrbitcoinpredict.entities.SimpleBitcoin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FullBitcoinBuilder {
    private FullBitcoin fullBitcoin;

    public FullBitcoinBuilder() {
    }

    public static FullBitcoinBuilder oneFullBitcoin(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        FullBitcoinBuilder fullBitcoinBuilder = new FullBitcoinBuilder();
        fullBitcoinBuilder.fullBitcoin = FullBitcoin.builder()
                .simpleBitcoin(
                        SimpleBitcoin.builder()
                                .open(111762.21f)
                                .high(119425.27f)
                                .low(110491.44f)
                                .close(113820.47f)
                                .build())
                .prediction(115040.65f)
                .date(LocalDate.parse("14/06/2022", formatter))
                .build();
        return fullBitcoinBuilder;
    }

    public FullBitcoinBuilder dateNull(){
        fullBitcoin.setDate(null);
        return this;
    }

    public FullBitcoin getNow(){
        return fullBitcoin;
    }

}
