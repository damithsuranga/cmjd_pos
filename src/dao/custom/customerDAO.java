package dao.custom;

import entity.customer;

import java.sql.SQLException;
import java.util.List;

public interface customerDAO {

    boolean SaveCustomer(customer customer) throws SQLException, ClassNotFoundException;
    boolean UpdateCustomer(customer customer) throws SQLException, ClassNotFoundException;
    boolean DeleteCustomer(customer customer) throws SQLException, ClassNotFoundException;
    customer SearchCustomer(String id) throws SQLException, ClassNotFoundException;
    List<customer> getallCustomer() throws SQLException, ClassNotFoundException;

}
