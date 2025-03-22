package com.nisum.app;

import graphql.GraphQLError;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    @GraphQlExceptionHandler(BookNotFoundException.class)
    public GraphQLError handleBookNotFoundException(BookNotFoundException ex){
        return GraphQLError.newError()
                .message(ex.getMessage())
                .build();
    }
}
