package com.course.warehouse.model;

/**
 * Заявка на отгрузку товара со склада.
 */
public class OutboundRequest extends StorageRequest {

    private String recipientName;

    public OutboundRequest(String productName, int quantity, String recipientName, String comment) {
        super(productName, quantity, comment);
        this.setRecipientName(recipientName);
    }

    @Override
    public String getRequestTypeLabel() {
        return "Отгрузка";
    }

    @Override
    public String buildDocumentText() {
        return "--- Заявка на отгрузку ---\n"
                + "Товар: " + this.getProductName() + "\n"
                + "Количество: " + this.getQuantity() + "\n"
                + "Получатель: " + this.recipientName + "\n"
                + "Комментарий: " + this.getComment() + "\n"
                + "Статус: " + this.getStatus().getLabel();
    }

    public String getRecipientName() {
        return this.recipientName;
    }

    public void setRecipientName(String recipientName) {
        if (recipientName == null || recipientName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя получателя не может быть пустым.");
        }
        this.recipientName = recipientName.trim();
    }
}
