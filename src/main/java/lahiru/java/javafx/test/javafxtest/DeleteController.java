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
import java.sql.*;

public class DeleteController {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtDeleteBookID;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtISBM;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQTY;
    @FXML
    void BackHome(ActionEvent event) {
        try {

            //create stage
            Stage stage = (Stage) this.root.getScene().getWindow();

            //scene load
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Home-Menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setScene(scene);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    @FXML
    void Cancle(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void Delete(ActionEvent event) {
        try {
            //load connector
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create connection with database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaFxTest", "root", "1212");

            String values = txtDeleteBookID.getText();
            //create sql query
            PreparedStatement stm = connection.prepareStatement("delete from add_book where BOOK_ID=?");
            stm.setObject(1,values);

            int delete = stm.executeUpdate();

            if (delete>0){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Successfully Deleted !");
                alert.show();
                clear();
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void search(ActionEvent event) {
        String id = txtDeleteBookID.getText();

        try {
            //load connector
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create connection with database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaFxTest", "root", "1212");

            //create sql query
            PreparedStatement stm = connection.prepareStatement("select * from add_book where BOOK_ID=?");
            stm.setObject(1,id);

            ResultSet resultSet = stm.executeQuery();

            while (resultSet.next()){
                txtName.setText(resultSet.getString(1));
                txtID.setText(resultSet.getString(2));
                txtISBM.setText(resultSet.getString(3));
                txtQTY.setText(resultSet.getString(4));
                txtPrice.setText(resultSet.getString(5));
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void clear() {
        txtDeleteBookID.setText("");
        txtName.setText("");
        txtID.setText("");
        txtISBM.setText("");
        txtQTY.setText("");
        txtPrice.setText("");
    }
}


