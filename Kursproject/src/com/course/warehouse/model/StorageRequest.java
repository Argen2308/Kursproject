package com.course.warehouse.model;

import com.course.warehouse.Documentable;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Абстрактная заявка на склад: общие поля и поведение для приёма и отгрузки.
 */
public abstract class StorageRequest implements Documentable {

    private static int idCounter = 1;

    private final int id;
    private String productName;
    private int quantity;
    private LocalDate createdAt;
    private RequestStatus status;
    private String comment;

    public StorageRequest(String productName, int quantity, String comment) {
        this.id = idCounter++;
        this.setProductName(productName);
        this.setQuantity(quantity);
        this.createdAt = LocalDate.now();
        this.status = RequestStatus.NEW;
        this.comment = comment != null ? comment : "";
    }

    /** Тип заявки для отображения и полиморфного вывода. */
    public abstract String getRequestTypeLabel();

    @Override
    public abstract String buildDocumentText();

    public String summaryLine() {
        return this.getRequestTypeLabel() + " №" + this.id + " | "
                + this.productName + " | " + this.quantity + " шт. | "
                + this.status.getLabel();
    }

    protected void validateQuantity(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(
                    "Количество должно быть положительным числом, указано: " + value);
        }
    }

    public int getId() {
        return this.id;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Название товара не может быть пустым.");
        }
        this.productName = productName.trim();
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.validateQuantity(quantity);
        this.quantity = quantity;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = Objects.requireNonNull(createdAt, "Дата не может быть null.");
    }

    public RequestStatus getStatus() {
        return this.status;
    }

    public void setStatus(RequestStatus status) {
        this.status = Objects.requireNonNull(status, "Статус не может быть null.");
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment != null ? comment : "";
    }
}
