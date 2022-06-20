package br.com.jcr.hrbitcoinpredict.exceptions;

public class BitcoinBrasilException extends RuntimeException{
    public  BitcoinBrasilException (){
        super();
    }
    public  BitcoinBrasilException (String menssage){
        super(menssage);
    }
}
