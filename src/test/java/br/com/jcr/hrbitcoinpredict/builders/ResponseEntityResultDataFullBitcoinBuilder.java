package br.com.jcr.hrbitcoinpredict.builders;

import br.com.jcr.hrbitcoinpredict.entities.FullBitcoin;
import br.com.jcr.hrbitcoinpredict.entities.ResultData;
import org.springframework.http.ResponseEntity;

import static br.com.jcr.hrbitcoinpredict.builders.ResultDataFullBitcoinBuilder.oneResultDataFullBitcoin;
import static br.com.jcr.hrbitcoinpredict.builders.ResultDataFullBitcoinBuilder.oneResultDataFullBitcoinDateNull;

public class ResponseEntityResultDataFullBitcoinBuilder {
    private ResponseEntity<ResultData<FullBitcoin>> responseEntity;

    public ResponseEntityResultDataFullBitcoinBuilder() {
    }

    public static ResponseEntityResultDataFullBitcoinBuilder oneResponseEntityResultDataFullBitcoin(){
        ResponseEntityResultDataFullBitcoinBuilder responseEntityResultDataFullBitcoinBuilder = new ResponseEntityResultDataFullBitcoinBuilder();
        responseEntityResultDataFullBitcoinBuilder.responseEntity = ResponseEntity.ok().body(oneResultDataFullBitcoin().getNow());
        return responseEntityResultDataFullBitcoinBuilder;
    }

    public static ResponseEntityResultDataFullBitcoinBuilder oneResponseEntityResultDataFullBitcoinDateNull(){
        ResponseEntityResultDataFullBitcoinBuilder responseEntityResultDataFullBitcoinBuilder = new ResponseEntityResultDataFullBitcoinBuilder();
        responseEntityResultDataFullBitcoinBuilder.responseEntity = ResponseEntity.ok().body(oneResultDataFullBitcoinDateNull().getNow());
        return responseEntityResultDataFullBitcoinBuilder;
    }

    public ResponseEntity<ResultData<FullBitcoin>> getNow(){
        return responseEntity;
    }

}
