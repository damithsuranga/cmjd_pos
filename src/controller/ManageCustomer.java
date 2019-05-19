package controller;

import business.customerBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import dto.customerDTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomerTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;

public class ManageCustomer {
    public JFXTextField txtCustomerID;
    public JFXTextField txtCustomerName;
    public JFXTextField txtAddress;
    public JFXTextField txtSalary;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public TableView<CustomerTM>CustomerTable;
    public JFXButton btnNew;
    public JFXButton btnRefresh;


    public void intialize() throws SQLException, ClassNotFoundException {

        CustomerTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        CustomerTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        CustomerTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
       CustomerTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("salary"));

       CustomerTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerTM>() {
           @Override
           public void changed(ObservableValue<? extends CustomerTM> observable, CustomerTM oldValue, CustomerTM newValue) {
               CustomerTM tm = observable.getValue();
               String id = tm.getId();
               String name = tm.getName();
               String address = tm.getAddress();
               double salary = tm.getSalary();

               txtCustomerID.setText(id);
               txtCustomerName.setText(name);
               txtAddress.setText(address);
               txtSalary.setText(salary+"");
           }
       });



        CustomerTable.refresh();



    }

    customerBO customerBO = new customerBO();

    public void SaveCustomerbutton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        customerDTO customerDTO = new customerDTO(txtCustomerID.getText(),txtCustomerName.getText(),txtAddress.getText(),Double.parseDouble(txtSalary.getText()));

        boolean result = customerBO.saveCustomer(customerDTO);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("customer Added");
        alert.show();


    }



    public void Deletebutton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Alert ConfirnMsg = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure delete this customer ?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = ConfirnMsg.showAndWait();

        if(buttonType.get()==ButtonType.YES){
            int selectedRow = CustomerTable.getSelectionModel().getSelectedIndex();

           // delete();
            customerDTO customerDTO = new customerDTO(txtCustomerID.getText());

           boolean result = customerBO.removeCustomer(customerDTO);

           if(result){
               CustomerTable.getItems().remove(CustomerTable.getSelectionModel().getSelectedItem());
           }
        }


        CustomerTable.refresh();
    }



    public void NewCustomerbutton(ActionEvent actionEvent) {

        generateCustomerID();

        reset();


    }


    public void ReFreshbutton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String SQL1 = "select * from customer";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(SQL1);
        ResultSet rst = pstm.executeQuery();

        while (rst.next()){
            String id = rst.getString(1);
            String name = rst.getString(2);
            String address = rst.getString(3);
            double salary = rst.getDouble(4);

            CustomerTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
            CustomerTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
            CustomerTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
            CustomerTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("salary"));



            ObservableList<CustomerTM> items = CustomerTable.getItems();
            items.add(new model.CustomerTM(id,name,address,salary));
        }
    }

    public void reset(){

        txtCustomerName.clear();
        txtAddress.clear();
        txtSalary.clear();
    }

   /* public void delete() throws SQLException, ClassNotFoundException {

        String CustomerID = txtCustomerID.getText();

        String SQL = "delete from customer where id=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement ptm = connection.prepareStatement(SQL);
       ptm.setObject(1,CustomerID);
        int i = ptm.executeUpdate();


    }*/

   public void generateCustomerID(){

       int random = new Random().nextInt(100);

       txtCustomerID.setText("C" + random);



   }
}
