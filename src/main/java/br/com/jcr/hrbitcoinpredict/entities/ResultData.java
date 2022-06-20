package br.com.jcr.hrbitcoinpredict.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultData<T> {

    private Integer status;

    private String message;

    private T body;

    public ResultData(Integer status, String message){
        this.status = status;
        this.message = message;
    }

}