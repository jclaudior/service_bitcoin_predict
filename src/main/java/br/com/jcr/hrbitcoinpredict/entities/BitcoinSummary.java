package br.com.jcr.hrbitcoinpredict.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BitcoinSummary {

    private LocalDate date;

    @JsonProperty("timestamp")
    private Long timeStamp;

    @JsonProperty("volume_cripto")
    private BigDecimal volumeCripto;

    @JsonProperty("volume_fiat")
    private Float volumeFiat;

    private Float high;

    private Float low;

    private Float open;

    private Float close;

    private Integer trades;

    @JsonProperty("avg_price")
    private Double avgPrice;

    @JsonIgnore
    public SimpleBitcoin toSimpleBitcoin(){
        SimpleBitcoin simpleBitcoin = SimpleBitcoin.builder()
                .open(open)
                .high(high)
                .low(low)
                .close(close)
                .build();
        return simpleBitcoin;
    }
}
