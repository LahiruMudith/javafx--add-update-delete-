package lahiru.java.javafx.test.javafxtest;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoadController implements Initializable {
    @FXML
    private TableView<LoadAllBookTM> tblLoadAllTable;

//code that load all data from db
    public ArrayList<LoadAllBookTM> loadAllData(){
        try {
            //load connector
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create connection with database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaFxTest", "root", "1212");

            //create sql query
            PreparedStatement stm = connection.prepareStatement("select * from add_book");

            ResultSet resultSet = stm.executeQuery();

            ArrayList<LoadAllBookTM> list = new ArrayList<>();

            while (resultSet.next()){
                LoadAllBookTM tm = new LoadAllBookTM();

                tm.setName(resultSet.getString(1));
                tm.setId(resultSet.getString(2));
                tm.setIsbm(resultSet.getString(3));
                tm.setQty(resultSet.getInt(4));
                tm.setPrice(resultSet.getDouble(5));

                list.add(tm);
            }

            return list;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblLoadAllTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblLoadAllTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblLoadAllTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("isbm"));
        tblLoadAllTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblLoadAllTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Price"));

        ArrayList<LoadAllBookTM> load = loadAllData();

        tblLoadAllTable.setItems(FXCollections.observableArrayList(load));

    }
}
