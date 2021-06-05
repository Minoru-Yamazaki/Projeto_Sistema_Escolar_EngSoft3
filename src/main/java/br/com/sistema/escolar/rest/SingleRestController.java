package br.com.sistema.escolar.rest;

import br.com.sistema.escolar.command.ICommand;
import br.com.sistema.escolar.exceptions.BusinessException;
import br.com.sistema.escolar.web.domain.EntidadeNegocio;
import br.com.sistema.escolar.web.domain.DefaultResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SingleRestController {

    private final Map<String, ICommand> commands;
    private static final String OPERACAO = "operacao";

    @RequestMapping
    public ResponseEntity<EntidadeNegocio> universalCrud(@RequestBody(required = false) Optional<String> body, @RequestParam Map<String, String> params) {

        String operacao = getTipoOperacao(params);

        ICommand command = this.commands.get(operacao);

        Optional<EntidadeNegocio> response = Optional.empty();//command.execute(new DefaultResponseMessage(params,body));

        return ResponseEntity.ok(response.orElse(null));
    }

    private String getTipoOperacao(Map<String, String> params) {
        String operacao = params.getOrDefault(OPERACAO,"");

        if(operacao.isEmpty())
            throw new BusinessException("Parametro tipo operacao nao encontrado");

        return operacao;
    }
}
