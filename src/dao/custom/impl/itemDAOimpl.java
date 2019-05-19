package dao.custom.impl;

import db.DBConnection;
import entity.item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class itemDAOimpl {


    public boolean SaveItem(item item) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into item values(?,?,?,?)");
        preparedStatement.setObject(1,item.getCode());
        preparedStatement.setObject(2,item.getDescription());
        preparedStatement.setObject(3,item.getUnitPrice());
        preparedStatement.setObject(4,item.getQtyOnHand());
        return preparedStatement.executeUpdate()>0;
    }

    public boolean UpdateItem(item item) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update item set description=?,unitPrice =?,qtyOnHand=? where code=?");
        preparedStatement.setObject(4,item.getCode());
        preparedStatement.setObject(1,item.getDescription());
        preparedStatement.setObject(2,item.getUnitPrice());
        preparedStatement.setObject(3,item.getQtyOnHand());
        return preparedStatement.executeUpdate()>0;
    }

    public boolean DeleteItem(item item) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from item where code=?");
        preparedStatement.setObject(1,item.getCode());
        return preparedStatement.executeUpdate()>0;
    }

    public item SearchItem(String code) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from item where code=?");
        preparedStatement.setObject(1,code);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return new item(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4));
        }
        return null;
    }

    public List<item> getallItem() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from item ");
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            getallItem().add(new item(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)));
        }
        return  getallItem();
    }

}
