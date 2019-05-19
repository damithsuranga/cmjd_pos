package dao.custom.impl;

import db.DBConnection;
import entity.itemdetail;
import entity.itemdetailPK;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class itemdetailDAO {

    public boolean SaveitemDetail(itemdetail idl) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into itemdetail values(?,?,?,?)");
        preparedStatement.setObject(1,idl.getItemdetailPK());
        preparedStatement.setObject(2,idl.getItemdetailPK());
        preparedStatement.setObject(3,idl.getQty());
        preparedStatement.setObject(4,idl.getUnitPrice());
        return preparedStatement.executeUpdate()>0;
    }

    public boolean UpdateItemDetail(itemdetail idl) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update itemdetail set qty=?,unitPrice=? where orderId=? and itemCode=?");
        preparedStatement.setObject(4,idl.getItemdetailPK());
        preparedStatement.setObject(3,idl.getItemdetailPK());
        preparedStatement.setObject(2,idl.getQty());
        preparedStatement.setObject(3,idl.getUnitPrice());
        return preparedStatement.executeUpdate()>0;
    }

    public boolean DeleteItemDetail(itemdetailPK idl) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from itemdetail where orderId=? and itemCode=?");
        preparedStatement.setObject(1,idl.getOrderId());
        preparedStatement.setObject(2,idl.getItemCode());
        return preparedStatement.executeUpdate()>0;
    }

    public itemdetail SearchItemDetail(itemdetailPK idl) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from itemdetail where orderId=? and itemCode=?");
        preparedStatement.setObject(1,idl.getOrderId());
        preparedStatement.setObject(1,idl.getItemCode());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return new itemdetail(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4));
        }
        return null;
    }

    public List<itemdetail> getallItemDetail() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from itemdetail ");
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            getallItemDetail().add(new itemdetail(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)));
        }
        return  getallItemDetail();
    }

}
