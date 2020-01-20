package com.company.case1;

import com.company.shared.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bios on 29.11.2017.
 */
public class ClientDAOImpl implements ClientDAO {

    private final Connection conn;

    public ClientDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void init() {
        try {
            Statement st = conn.createStatement();
            try {
                st.execute("DROP TABLE IF EXISTS Clients");
                st.execute("CREATE TABLE Clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) NOT NULL, age INT)");
            } finally {
                st.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void addClient(String name, int age) {
        try {
            try (PreparedStatement st = conn
                    .prepareStatement("INSERT INTO Clients (name, age) VALUES(?, ?)")) {
                st.setString(1, name);
                st.setInt(2, age);
                st.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteClient(int id) {
        //...
    }

    @Override
    public List<Client> getAll() {
        List<Client> res = new ArrayList<>();

        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT * FROM Clients")) {
                    while (rs.next()) {
                        Client client = new Client();

                        client.setId(rs.getInt(1));
                        client.setName(rs.getString(2));
                        client.setAge(rs.getInt(3));

                        res.add(client);
                    }
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public long count() {
        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM Clients")) {
                    if (rs.next())
                        return rs.getLong(1);
                    else
                        throw new RuntimeException("Count failed");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
