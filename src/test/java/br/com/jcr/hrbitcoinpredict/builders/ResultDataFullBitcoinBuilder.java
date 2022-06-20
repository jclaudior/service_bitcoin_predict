package br.com.jcr.hrbitcoinpredict.builders;

import br.com.jcr.hrbitcoinpredict.entities.FullBitcoin;
import br.com.jcr.hrbitcoinpredict.entities.ResultData;

import static br.com.jcr.hrbitcoinpredict.builders.FullBitcoinBuilder.oneFullBitcoin;

public class ResultDataFullBitcoinBuilder {
    private ResultData<FullBitcoin> resultData;

    public ResultDataFullBitcoinBuilder() {
    }

    public static ResultDataFullBitcoinBuilder oneResultDataFullBitcoin(){
        ResultDataFullBitcoinBuilder ResultDataFullBitcoinBuilder =  new ResultDataFullBitcoinBuilder();
        ResultDataFullBitcoinBuilder.resultData = new ResultData<>(
                200,
                "Predição realizada com sucesso!",
                        oneFullBitcoin().getNow()
        );
        return ResultDataFullBitcoinBuilder;
    }

    public static ResultDataFullBitcoinBuilder oneResultDataFullBitcoinDateNull(){
        ResultDataFullBitcoinBuilder ResultDataFullBitcoinBuilder =  new ResultDataFullBitcoinBuilder();
        ResultDataFullBitcoinBuilder.resultData = new ResultData<>(
                200,
                "Predição realizada com sucesso!",
                oneFullBitcoin().dateNull().getNow()
        );
        return ResultDataFullBitcoinBuilder;
    }

    public ResultData<FullBitcoin> getNow(){
        return resultData;
    }
}
