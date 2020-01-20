package com.company.case2;

import com.company.shared.Client;
import com.company.shared.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final String DB_CONNECTION =
            "jdbc:mysql://localhost:3306/prog_db.jdbcdao1?serverTimezone=Europe/Kiev";
    static final String DB_USER = "admin";
    static final String DB_PASSWORD = "admin";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        ConnectionFactory factory = new ConnectionFactory(
                DB_CONNECTION, DB_USER, DB_PASSWORD
        );

        Connection conn = factory.getConnection();
        try {
            ClientDAOEx dao = new ClientDAOEx(conn, "clients");

            Client c = new Client("test", 1);
            dao.add(c);

            List<Client> list = dao.getAll(Client.class);
            for (Client cli : list)
                System.out.println(cli);

            //Client c1 = list.get(0);
            //c1.setAge(44);
            //dao.update(c1);

             //List<Object[]> res1 = dao.getAll(Client.class);

            dao.delete(list.get(0));
        } finally {
            sc.close();
            if (conn != null) conn.close();
        }
    }
}
