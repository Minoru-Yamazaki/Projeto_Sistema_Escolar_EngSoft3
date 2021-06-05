package br.com.sistema.escolar.configuration;

import br.com.sistema.escolar.command.ICommand;
import br.com.sistema.escolar.command.SalvarCommand;
import br.com.sistema.escolar.services.MateriaServices;
import br.com.sistema.escolar.usecase.crud.materia.MateriaFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static br.com.sistema.escolar.configuration.CommandOperation.*;

@Configuration
public class AppConfiguration {

    @Bean
    public Map<CommandOperation, ICommand> factoryCommand(MateriaFacade materiaFacade) {
        Map<CommandOperation, ICommand> factory = new HashMap<>();

        factory.put(MATERIA_SALVAR, new SalvarCommand(materiaFacade));

        return factory;
    }
}
