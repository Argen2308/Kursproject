package com.course.warehouse.model;

/**
 * Заявка на приём товара на склад.
 */
public class InboundRequest extends StorageRequest {

    private String supplierName;

    public InboundRequest(String productName, int quantity, String supplierName, String comment) {
        super(productName, quantity, comment);
        this.setSupplierName(supplierName);
    }

    @Override
    public String getRequestTypeLabel() {
        return "Приёмка";
    }

    @Override
    public String buildDocumentText() {
        return "--- Заявка на приём ---\n"
                + "Товар: " + this.getProductName() + "\n"
                + "Количество: " + this.getQuantity() + "\n"
                + "Поставщик: " + this.supplierName + "\n"
                + "Комментарий: " + this.getComment() + "\n"
                + "Статус: " + this.getStatus().getLabel();
    }

    public String getSupplierName() {
        return this.supplierName;
    }

    public void setSupplierName(String supplierName) {
        if (supplierName == null || supplierName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя поставщика не может быть пустым.");
        }
        this.supplierName = supplierName.trim();
    }
}
