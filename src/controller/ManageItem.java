package controller;

import com.jfoenix.controls.JFXButton;
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
import model.ItemTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;

public class ManageItem {
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXButton btnSave;
    public JFXButton btndelete;
    public TableView<ItemTM> tblItem;
    public JFXButton btnItem;
    public JFXButton btnRefrsh;

    public void initialize(){

        tblItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qtyonHand"));

        tblItem.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ItemTM>() {
            @Override
            public void changed(ObservableValue<? extends ItemTM> observable, ItemTM oldValue, ItemTM newValue) {
                ItemTM tm = observable.getValue();
                String code = tm.getCode();
                String description = tm.getDescription();
                double unitPrice = tm.getUnitPrice();
                Integer qtyonHand = tm.getQtyonHand();
            }
        });

    }

    public void NewItembutton(ActionEvent actionEvent) {

        Allclear();
        genarateItemCode();
    }



    public void SaveItembutton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String code = txtCode.getText();
        String description = txtDescription.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        Integer qtyonHand = Integer.parseInt(txtQtyOnHand.getText());

        String SQL = "insert into item values(?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(SQL);
        pstm.setObject(1,code);
        pstm.setObject(2,description);
        pstm.setObject(3,unitPrice);
        pstm.setObject(4,qtyonHand);
        boolean b = pstm.executeUpdate()>0;

        if(b){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Item Added");
            alert.show();

        }


    }

    public void DeleteItembutton(ActionEvent actionEvent) {

        Alert confiorm = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure delete item", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> button = confiorm.showAndWait();

        if(button.get()==ButtonType.YES){
            int selectedIndex = tblItem.getSelectionModel().getSelectedIndex();

        }

        tblItem.getItems().remove(tblItem.getSelectionModel().getSelectedItem());

    }

    public void Refreshbutton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String SQL = "select * from item";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement patm = connection.prepareStatement(SQL);
        ResultSet rst = patm.executeQuery();
        while(rst.next()){

            String code = rst.getString(1);
            String description = rst.getString(2);
            double unitPrice = rst.getDouble(3);
            Integer qtyonHand = rst.getInt(4);

            tblItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
            tblItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
            tblItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            tblItem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qtyonHand"));

            ObservableList<ItemTM> item = tblItem.getItems();
            item.add(new ItemTM(code,description,unitPrice,qtyonHand));


        }

    }

    public void Allclear(){

        txtDescription.clear();
        txtQtyOnHand.clear();
        txtUnitPrice.clear();
    }

    public  void genarateItemCode(){

        int random = new Random().nextInt(1000);
        txtCode.setText("IT"+random);

    }
}
