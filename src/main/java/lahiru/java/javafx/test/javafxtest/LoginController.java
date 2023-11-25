package lahiru.java.javafx.test.javafxtest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private AnchorPane root;


    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void Login(ActionEvent event) throws IOException {
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        System.out.println(email +" "+ password);

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


}
