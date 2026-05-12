package com.course.warehouse.service;

import com.course.warehouse.exception.RequestNotFoundException;
import com.course.warehouse.model.RequestStatus;
import com.course.warehouse.model.StorageRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * Склад хранит заявки и операции над ними (инкапсуляция списка).
 */
public class Warehouse {

    private final List<StorageRequest> requests;

    public Warehouse() {
        this.requests = new ArrayList<>();
    }

    public void register(StorageRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Заявка не может быть null.");
        }
        this.requests.add(request);
    }

    public List<StorageRequest> getAllSortedById() {
        List<StorageRequest> copy = new ArrayList<>(this.requests);
        copy.sort(Comparator.comparingInt(StorageRequest::getId));
        return Collections.unmodifiableList(copy);
    }

    public StorageRequest findById(int id) throws RequestNotFoundException {
        for (StorageRequest r : this.requests) {
            if (r.getId() == id) {
                return r;
            }
        }
        throw new RequestNotFoundException("Заявка №" + id + " не найдена.");
    }

    public void updateStatus(int id, RequestStatus newStatus) throws RequestNotFoundException {
        StorageRequest r = this.findById(id);
        r.setStatus(newStatus);
    }

    public int count() {
        return this.requests.size();
    }
}
