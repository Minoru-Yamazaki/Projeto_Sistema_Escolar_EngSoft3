package br.com.sistema.escolar.exceptions;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends RuntimeException {

    private List<String> messages = new ArrayList<>();

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(List<String> messages){
        this.messages = new ArrayList<>(messages);
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }
}
