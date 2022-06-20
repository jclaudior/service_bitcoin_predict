package br.com.jcr.hrbitcoinpredict.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SimpleBitcoinPredict {
    private Float open;
    private Float high;
    private Float low;

    @JsonIgnore
    public SimpleBitcoin toSimpleBitcoin(){
        return SimpleBitcoin.builder().open(open).high(high).low(low).build();
    }

}
