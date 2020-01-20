package com.company.case1;

import com.company.shared.Client;

import java.util.List;

public interface ClientDAO {
    void init();
    void addClient(String name, int age);
    void deleteClient(int id);
    List<Client> getAll();
    long count();
}
