package com.course.warehouse.model;

/**
 * Статусы складской заявки.
 */
public enum RequestStatus {
    NEW("Новая"),
    IN_PROGRESS("В работе"),
    DONE("Выполнена");

    private final String label;

    RequestStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
