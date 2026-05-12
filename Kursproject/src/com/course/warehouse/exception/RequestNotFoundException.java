package com.course.warehouse.exception;

/**
 * Выбрасывается, если заявка с указанным номером не найдена.
 */
public class RequestNotFoundException extends Exception {

    public RequestNotFoundException(String message) {
        super(message);
    }
}
