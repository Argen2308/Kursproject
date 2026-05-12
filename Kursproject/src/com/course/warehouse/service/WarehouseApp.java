package com.course.warehouse;

import com.course.warehouse.exception.RequestNotFoundException;
import com.course.warehouse.model.InboundRequest;
import com.course.warehouse.model.OutboundRequest;
import com.course.warehouse.model.RequestStatus;
import com.course.warehouse.model.StorageRequest;
import com.course.warehouse.service.Warehouse;

import java.util.List;
import java.util.Scanner;

/**
 * Консольное приложение: управление заявками склада (приёмка / отгрузка).
 */
public final class WarehouseApp {

    private WarehouseApp() {
    }

    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        seedDemo(warehouse);

        Scanner scanner = new Scanner(System.in);
        try {
            mainLoop(scanner, warehouse);
        } finally {
            scanner.close();
            System.out.println("Завершение работы программы.");
        }
    }

    private static void seedDemo(Warehouse warehouse) {
        warehouse.register(new InboundRequest("Бумага А4", 100, "ООО «Поставка»", "Срочно"));
        warehouse.register(new OutboundRequest("Мониторы", 5, "Магазин «Техно»", "Проверить упаковку"));
    }

    private static void mainLoop(Scanner scanner, Warehouse warehouse) {
        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();
            try {
                if ("1".equals(choice)) {
                    registerInbound(scanner, warehouse);
                } else if ("2".equals(choice)) {
                    registerOutbound(scanner, warehouse);
                } else if ("3".equals(choice)) {
                    listRequests(warehouse);
                } else if ("4".equals(choice)) {
                    changeStatus(scanner, warehouse);
                } else if ("5".equals(choice)) {
                    showDocument(scanner, warehouse);
                } else if ("6".equals(choice)) {
                    polymorphismDemo(warehouse);
                } else if ("0".equals(choice)) {
                    return;
                } else {
                    System.out.println("Неизвестная команда. Повторите ввод.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Ожидалось число. Попробуйте снова.");
            } catch (IllegalArgumentException ex) {
                System.out.println("Ошибка ввода или данных: " + ex.getMessage());
            } catch (RequestNotFoundException ex) {
                System.out.println("Ошибка: " + ex.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("========== Управление заявками склада ==========");
        System.out.println("1 — Зарегистрировать заявку на приём");
        System.out.println("2 — Зарегистрировать заявку на отгрузку");
        System.out.println("3 — Список всех заявок");
        System.out.println("4 — Изменить статус заявки");
        System.out.println("5 — Показать «документ» заявки (интерфейс Documentable)");
        System.out.println("6 — Демонстрация полиморфизма (краткие строки по всем)");
        System.out.println("0 — Выход");
        System.out.print("Выберите пункт: ");
    }

    private static void registerInbound(Scanner scanner, Warehouse warehouse) {
        System.out.print("Название товара: ");
        String product = scanner.nextLine();
        System.out.print("Количество (целое > 0): ");
        int qty = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Поставщик: ");
        String supplier = scanner.nextLine();
        System.out.print("Комментарий (можно пусто): ");
        String comment = scanner.nextLine();

        InboundRequest req = new InboundRequest(product, qty, supplier, comment);
        warehouse.register(req);
        System.out.println("Создана заявка: " + req.summaryLine());
    }

    private static void registerOutbound(Scanner scanner, Warehouse warehouse) {
        System.out.print("Название товара: ");
        String product = scanner.nextLine();
        System.out.print("Количество (целое > 0): ");
        int qty = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Получатель: ");
        String recipient = scanner.nextLine();
        System.out.print("Комментарий (можно пусто): ");
        String comment = scanner.nextLine();

        OutboundRequest req = new OutboundRequest(product, qty, recipient, comment);
        warehouse.register(req);
        System.out.println("Создана заявка: " + req.summaryLine());
    }

    private static void listRequests(Warehouse warehouse) {
        List<StorageRequest> list = warehouse.getAllSortedById();
        if (list.isEmpty()) {
            System.out.println("Заявок пока нет.");
            return;
        }
        for (StorageRequest r : list) {
            System.out.println(r.summaryLine());
        }
    }

    private static void changeStatus(Scanner scanner, Warehouse warehouse) throws RequestNotFoundException {
        System.out.print("Номер заявки: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Новый статус: 1 — NEW, 2 — IN_PROGRESS, 3 — DONE");
        int s = Integer.parseInt(scanner.nextLine().trim());
        RequestStatus status;
        switch (s) {
            case 1:
                status = RequestStatus.NEW;
                break;
            case 2:
                status = RequestStatus.IN_PROGRESS;
                break;
            case 3:
                status = RequestStatus.DONE;
                break;
            default:
                throw new IllegalArgumentException("Неверный код статуса.");
        }
        warehouse.updateStatus(id, status);
        System.out.println("Статус обновлён для заявки №" + id + ".");
    }

    private static void showDocument(Scanner scanner, Warehouse warehouse) throws RequestNotFoundException {
        System.out.print("Номер заявки: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        StorageRequest r = warehouse.findById(id);
        System.out.println(r.buildDocumentText());
    }

    /** Полиморфизм: одна переменная типа StorageRequest, разное поведение подклассов. */
    private static void polymorphismDemo(Warehouse warehouse) {
        System.out.println("--- Полиморфный вывод getRequestTypeLabel() / summaryLine() ---");
        for (StorageRequest r : warehouse.getAllSortedById()) {
            System.out.println(r.getRequestTypeLabel() + ": " + r.summaryLine());
        }
    }
}
