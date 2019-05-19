package dao.custom.impl;

import dao.custom.customerDAO;
import db.DBConnection;
import entity.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class customerDAOimpl implements customerDAO {

    public boolean SaveCustomer(customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into customer values(?,?,?,?)");
        preparedStatement.setObject(1,customer.getId());
        preparedStatement.setObject(2,customer.getName());
        preparedStatement.setObject(3,customer.getAddress());
        preparedStatement.setObject(4,customer.getSalary());
        return preparedStatement.executeUpdate()>0;
    }

    public boolean UpdateCustomer(customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update customer set name=?,address =?,salary=? where id=?");
        preparedStatement.setObject(4,customer.getId());
        preparedStatement.setObject(1,customer.getName());
        preparedStatement.setObject(2,customer.getAddress());
        preparedStatement.setObject(3,customer.getSalary());
        return preparedStatement.executeUpdate()>0;
    }

    public boolean DeleteCustomer(customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from customer where id=?");
        preparedStatement.setObject(1,customer.getId());
        return preparedStatement.executeUpdate()>0;
    }

    public customer SearchCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from customer where id=?");
        preparedStatement.setObject(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
       if (resultSet.next()){
            return new customer(resultSet.getString(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getDouble(4));
        }
        return null;
    }

    public List<customer>getallCustomer() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from customer ");
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            getallCustomer().add(new customer(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)));
        }
        return  getallCustomer();
    }
}
