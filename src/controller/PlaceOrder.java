package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.OrderDetailsTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

public class PlaceOrder {
    public JFXTextField txtOrderDate;
    public JFXTextField txtCustomerName;
    public JFXComboBox cmbOrderID;
    public JFXComboBox cmbCustomerID;
    public JFXComboBox cmbItemCode;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQuantityOnHand;
    public JFXTextField txtQuantity;
    public JFXButton btnNewOrder;
    public JFXButton btnSave;
    public TableView<OrderDetailsTM> tblPlaceOrder;
    public JFXTextField txtTotal;
    public JFXTextField txtPaid;
    public JFXTextField txtDiscount;
    public JFXTextField txtBalance;
    public JFXTextField txtNetTotal;
    public JFXButton btnDelete;
    public JFXTextField txtOrderID;
    public JFXTextField txtDate;
    public JFXTextField txtdescription;
    public JFXButton btnPlaceOrder;
    public ImageView placeImage;
    private LocalDate OrderDate;

    public void initialize()  {

        tblPlaceOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblPlaceOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblPlaceOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        tblPlaceOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblPlaceOrder.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Total"));

        OrderDate = LocalDate.now();
        txtOrderDate.setText(OrderDate.toString());
        txtOrderDate.setEditable(false);

        try {
            String SQL = "select id from customer";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psrt = connection.prepareStatement(SQL);
            ResultSet rst = psrt.executeQuery();

            while (rst.next()) {

                String customerID = rst.getString(1);
                cmbCustomerID.getItems().add(customerID);

            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }

        cmbCustomerID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                try {
                    Connection connection1 = DBConnection.getInstance().getConnection();
                    PreparedStatement patm = connection1.prepareStatement("select name from customer where id=?");
                    patm.setObject(1,newValue);
                    ResultSet resultSet = patm.executeQuery();
                    resultSet.next();
                    String customerName = resultSet.getString(1);
                    txtCustomerName.setText(customerName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        try {


            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("select code from item");
            ResultSet rset = pstm.executeQuery();

            while (rset.next()) {
                String code = rset.getString(1);
                cmbItemCode.getItems().add(code);
            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    Connection connection = DBConnection.getInstance().getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("select description,unitPrice,qtyOnHand from item where code=?");
                    preparedStatement.setObject(1,newValue);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        String description = resultSet.getString(1);
                        double unitPrice = resultSet.getDouble(2);
                        Integer qtyonHand = resultSet.getInt(3);
                        txtdescription.setText(description);
                        txtUnitPrice.setText(unitPrice+"");
                        txtQuantityOnHand.setText(qtyonHand+"");
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void NewOrderbutton(ActionEvent actionEvent) {

        clearAll();
        generateOrderID();
    }

    public void SavePlaceOrder(ActionEvent actionEvent) {

        int Quantity=0;

        int QuantityonHand = Integer.parseInt(txtQuantityOnHand.getText());

        if(cmbCustomerID.getSelectionModel().getSelectedIndex()==-1){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Customer ID should be select before adding Order");
            a.show();
       }

        try {
            Quantity = Integer.parseInt(txtQuantity.getText());
        }catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.ERROR, "invalid Quantity").show();
            txtQuantity.requestFocus();
            return;
        }


        if(Quantity<1 || Quantity>QuantityonHand){
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setContentText("Maximum Quantity is"+txtQuantityOnHand.getText());
            a2.show();

        }


        tblPlaceOrder.getItems().add(new OrderDetailsTM(cmbItemCode.getSelectionModel().getSelectedItem().toString(),
                txtdescription.getText(),
                Integer.parseInt(txtQuantity.getText()),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQuantity.getText() )* Double.parseDouble(txtUnitPrice.getText())));

        calculateTotal();

    }

    public void deletebutton(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure delete this Order", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> button = alert.showAndWait();

        if(button.get()==ButtonType.YES){
            int selectedRow = tblPlaceOrder.getSelectionModel().getSelectedIndex();

            tblPlaceOrder.getItems().remove(tblPlaceOrder.getSelectionModel().getSelectedItem());

        }

        tblPlaceOrder.refresh();

    }

    public void clearAll(){
        txtCustomerName.clear();
        txtdescription.clear();
        txtQuantity.clear();
        txtUnitPrice.clear();
        txtQuantityOnHand.clear();
        txtTotal.clear();
        txtNetTotal.clear();
        txtBalance.clear();
        txtDiscount.clear();
        txtPaid.clear();
       // cmbCustomerID.getSelectionModel().clearSelection();
        cmbItemCode.getSelectionModel().clearSelection();
        tblPlaceOrder.getSelectionModel().clearSelection();
    }

    public void generateOrderID(){
        int random = new Random().nextInt(100);
        txtOrderID.setText("OD"+random);
    }

    public void calculateTotal(){

        ObservableList<OrderDetailsTM> item = tblPlaceOrder.getItems();
        double total=0;
        for (OrderDetailsTM items:item
             ) {

            total+= items.getTotal();

        }
        txtTotal.setText(total+"");
    }

    public void PlaceOrderbutton(ActionEvent actionEvent) {
        String OrderID = txtOrderID.getText();

        boolean result = false;
        Connection connection = null;

        try {
            connection = DBConnection.getInstance().getConnection();

            connection.setAutoCommit(false);
            PreparedStatement pst = connection.prepareStatement("insert into orders values(?,?,?)");
            pst.setObject(1,OrderID);
            pst.setObject(2,txtOrderDate.getText());
            pst.setObject(3,cmbCustomerID.getValue());
             result = pst.executeUpdate()>0;

             if(!result){
                 connection.rollback();
                 return;
             }

             pst = connection.prepareStatement("insert into itemdetail values(?,?,?,?)");
             PreparedStatement pst1 = connection.prepareStatement("update item set qtyOnHand=qtyOnHand-? where code=?");
                ObservableList<OrderDetailsTM> items =tblPlaceOrder.getItems();

                for (OrderDetailsTM item:items)
                      {
                          pst.setObject(1,OrderID);
                          pst.setObject(2,item.getCode());
                          pst.setObject(3,item.getUnitPrice());
                          pst.setObject(4,item.getQuantity());

                         result = pst.executeUpdate()>0;

                         if(!result){
                             connection.rollback();
                             return;
                         }

                         pst1.setObject(1,item.getQuantity());
                         pst1.setObject(2,item.getCode());
                         result = pst1.executeUpdate()>0;
                }
                connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try{
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(result){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Order Added");
            alert.show();
        }

        generateNetTotal();

    }

    public void generateNetTotal(){

        double NetTotal = 0;
        double Balance = 0;
        double PaidValue = Double.parseDouble(txtPaid.getText());
        double Discount = Double.parseDouble(txtDiscount.getText());
        double total = Double.parseDouble(txtTotal.getText());

        if(Discount>0){

            NetTotal = total - (total*(Discount/100));
            txtNetTotal.setText(NetTotal+"");
            Balance = PaidValue - NetTotal;
            txtBalance.setText(Balance+"");

        }else{

            Balance = PaidValue - (total);
            txtBalance.setText(Balance+"");
            txtNetTotal.setText(Balance+"");

        }



    }


}
