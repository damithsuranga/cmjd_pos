package dao.custom.impl;

import db.DBConnection;
import entity.orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ordersDAO {

    public boolean SaveOrder(orders orders) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into orders values(?,?,?)");
        preparedStatement.setObject(1,orders.getId());
        preparedStatement.setObject(2,orders.getDate());
        preparedStatement.setObject(3,orders.getId());
        return preparedStatement.executeUpdate()>0;
    }

    public boolean UpdateOrder(orders orders) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update orders set date=?,customerId =? where id=?");
        preparedStatement.setObject(3,orders.getId());
        preparedStatement.setObject(1,orders.getDate());
        preparedStatement.setObject(2,orders.getCustomerId());
        return preparedStatement.executeUpdate()>0;
    }

    public boolean DeleteOrder(orders orders) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from orders where id=?");
        preparedStatement.setObject(1,orders.getId());
        return preparedStatement.executeUpdate()>0;
    }

    public orders SearchOrder(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from orders where id=?");
        preparedStatement.setObject(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return new orders(resultSet.getString(1),
                    resultSet.getDate(2),
                    resultSet.getString(3));
        }
        return null;
    }

    public List<orders> getallOrderr() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from customer ");
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            getallOrderr().add(new orders(resultSet.getString(1),
                    resultSet.getDate(2),
                    resultSet.getString(3)));
        }
        return  getallOrderr();
    }

}
