package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.text.LabelView;
import java.io.IOException;

public class MainMenu {
    public JFXButton btnCustomer;
    public JFXButton btnItem;
    public JFXButton btnPlace;
    public JFXButton btnOrders;
    public Label iblNote;
    public Label ibldescription;

    public void navigate(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/ManageCustomer.fxml"));
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.show();

    }

    public void navigate1(MouseEvent mouseEvent) throws IOException {

        Parent root1 = FXMLLoader.load(getClass().getResource("/view/ManageItem.fxml"));
        Scene scene1 = new Scene(root1);
        Stage newStage1 = new Stage();
        newStage1.setScene(scene1);
        newStage1.show();
    }

    public void navigate2(MouseEvent mouseEvent) throws IOException {

        Parent root2 = FXMLLoader.load(getClass().getResource("/view/PlaceOrder.fxml"));
        Scene scene2 = new Scene(root2);
        Stage stage2 = new Stage();
        stage2.setScene(scene2);
        stage2.show();
    }


    public void mouseIsEnterd(MouseEvent mouseEvent) {

        JFXButton button = (JFXButton) mouseEvent.getSource();

        switch (button.getId()){

            case "btnCustomer":
                iblNote.setText("Manage Customer");
                ibldescription.setText("Click to save & delete customer");
                break;
            case "btnItem":
                iblNote.setText("Manage Item");
                ibldescription.setText("Click to save & delete Item");
                break;
            case "btnPlace":
                iblNote.setText("Order Details");
                ibldescription.setText("Click to Place Order & generate bill");
                break;
            case "btnOrders":
                iblNote.setText("Orders");
                ibldescription.setText("Click to search Orders");
                break;

                default:
                    iblNote.setText("Welcome");
                    ibldescription.setText("Please Select one of above Operation to Proceed");

        }
    }

    public void mouseIsExit(MouseEvent mouseEvent) {

        JFXButton button = (JFXButton) mouseEvent.getSource();

        button.setEffect(null);
        iblNote.setText("Welcome");
        ibldescription.setText("Please Select one of above Operation to Proceed");

    }
}
