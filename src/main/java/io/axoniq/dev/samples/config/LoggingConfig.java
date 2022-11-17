package io.axoniq.dev.samples.config;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.ConfigurerModule;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.lifecycle.Phase;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.interceptors.LoggingInterceptor;
import org.axonframework.queryhandling.QueryBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {

    @Bean
    public LoggingInterceptor<Message<?>> loggingInterceptor() {
        return new LoggingInterceptor<>();
    }

    @Bean
    public ConfigurerModule loggingInterceptorConfigurerModule(LoggingInterceptor<Message<?>> loggingInterceptor) {
        return configurer -> {
            configurer.onInitialize(config -> config.onStart(Phase.INSTRUCTION_COMPONENTS, () -> {
                CommandBus commandBus = config.commandBus();
                commandBus.registerDispatchInterceptor(loggingInterceptor);
                commandBus.registerHandlerInterceptor(loggingInterceptor);
                EventBus eventBus = config.eventBus();
                eventBus.registerDispatchInterceptor(loggingInterceptor);
                QueryBus queryBus = config.queryBus();
                queryBus.registerDispatchInterceptor(loggingInterceptor);
                queryBus.registerHandlerInterceptor(loggingInterceptor);
            }));
            configurer.eventProcessing()
                    .registerDefaultHandlerInterceptor((c, processorName) -> loggingInterceptor);
        };
    }
}

