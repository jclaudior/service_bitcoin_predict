package br.com.jcr.hrbitcoinpredict.entities;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FullBitcoin {
    private SimpleBitcoin simpleBitcoin;
    private Float prediction;
    private LocalDate date;

    public Boolean getAscend(){
        if (simpleBitcoin.getOpen() < prediction)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

}
