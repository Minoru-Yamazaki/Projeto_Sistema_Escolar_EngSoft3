package br.com.sistema.escolar.web.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class DefaultResponseMessage extends EntidadeNegocio {

    private String message = "";
    private List<String> messages = new ArrayList<>();
    private boolean valid;

    public static DefaultResponseMessage validMessage(String message) {
        return DefaultResponseMessage.builder()
                                .valid(true)
                                .message(message).build();
    }
}
