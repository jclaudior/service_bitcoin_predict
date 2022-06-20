package br.com.jcr.hrbitcoinpredict.utils.models;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogPattern {
    private String classLogging;
    private String methodLogging;
    private String message;
    private Integer status;
    private String data;
    private LocalDateTime dateTime;

    @Override
    public String toString(){
        return String.format(
                "class={%s} method={%s} message={%s} status={%d} data={%s} dateTime={%s}",
                classLogging,
                methodLogging,
                message,
                status,
                data,
                dateTime
        );
    }
}
