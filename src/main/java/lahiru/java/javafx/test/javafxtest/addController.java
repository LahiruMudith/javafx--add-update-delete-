package lahiru.java.javafx.test.javafxtest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addController {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtIsbm;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;
    @FXML
    void BackHome(ActionEvent event) {
        try {

            //create stage
            Stage stage = (Stage) this.root.getScene().getWindow();

            //scene load
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Home-menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setScene(scene);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void Add(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String isbm = txtIsbm.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price = Double.parseDouble(txtPrice.getText());
        System.out.println("Name :" +name+ " | " + "ID :" +id+ " | " + "Price:" +price );

        try {
            //load connector
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create connection with database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaFxTest", "root", "1212");

            //create sql query
            PreparedStatement stm = connection.prepareStatement("insert into add_Book values(?,?,?,?,?)");
            stm.setObject(1,name);
            stm.setObject(2,id);
            stm.setObject(3,isbm);
            stm.setObject(4,qty);
            stm.setObject(5,price);

            int result = stm.executeUpdate();

            if (result>0) {
                System.out.println("Added successfully !");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Added successfully");
                alert.show();
                clear();
            }else {
                System.out.println("Added Failed !");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Added Failed");
                alert.show();
                clear();
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void Cancle(ActionEvent event) {
        System.exit(0);
    }

    public void clear() {
        txtId.setText("");
        txtName.setText("");
        txtIsbm.setText("");
        txtQty.setText("");
        txtPrice.setText("");
    }
}


