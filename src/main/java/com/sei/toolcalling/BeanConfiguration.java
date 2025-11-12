package com.sei.toolcalling;

import com.sei.toolcalling.domain.port.api.TodoServicePort;
import com.sei.toolcalling.domain.port.spi.TodoPersistencePort;
import com.sei.toolcalling.domain.service.TodoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public TodoServicePort todoServicePort(TodoPersistencePort todoPersistencePort) {
        return new TodoServiceImpl(todoPersistencePort);
    }
}
