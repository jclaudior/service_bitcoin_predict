package br.com.jcr.hrbitcoinpredict.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SimpleBitcoin {
    private Float open;
    private Float high;
    private Float low;
    private Float close;

    @JsonIgnore
    public float[] getInputPredict(){
        float inputBitcoin[] = {open, high, low};
        return  inputBitcoin;
    }
}
