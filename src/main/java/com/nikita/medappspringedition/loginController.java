package com.nikita.medappspringedition;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Patient, String> age;

    @FXML
    private TableColumn<Patient, String> dateOfBirth;

    @FXML
    private TableColumn<Patient, String> diagnos;

    @FXML
    private TableColumn<Patient, String> firstVisitDate;

    @FXML
    private TableColumn<Patient, String> fullName;

    @FXML
    private TableColumn<Patient, String> homeAdress;

    @FXML
    private TableColumn<Patient, String> lastVisitDate;

    @FXML
    private TableColumn<Patient, String> locality;

    @FXML
    private TableColumn<Patient, String> male;

    @FXML
    private TableColumn<Patient, String> socialStatus;

    @FXML
    private TableColumn<Patient, String> treatment;

    @FXML
    private TableColumn<Patient, String> patId;

    @FXML
    private TableView<Patient> myTable;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonShow;

    @FXML
    void initialize() {
        buttonBack.setOnAction(event -> {
            buttonBack.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("com.nikita.medappspringedition/hello-view.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
        buttonShow.setOnAction(event -> {
            DatabaseHandler dbHandler = new DatabaseHandler();
            ObservableList<Patient> data = FXCollections.observableArrayList();
            try {
                ResultSet ids = dbHandler.selectIDs();
                ResultSet fullNames = dbHandler.selectNames();
                ResultSet males = dbHandler.selectMales();
                ResultSet datesOfBirths = dbHandler.selectDatesOfBirths();
                ResultSet ages = dbHandler.selectAges();
                ResultSet localities = dbHandler.selectLocalities();
                ResultSet homeAdresses = dbHandler.selectHomeAdresses();
                ResultSet socialStatuses = dbHandler.selectSocialStatuses();
                ResultSet diagnoses = dbHandler.selectDiagnosis();
                ResultSet lastVisitsDates = dbHandler.selectLastVisitsDates();
                ResultSet firstVisitsDates = dbHandler.selectFirstVisitsDates();
                ResultSet treatments = dbHandler.selectTreatments();

                while (ids.next()) {
                    fullNames.next();
                    males.next();
                    datesOfBirths.next();
                    ages.next();
                    localities.next();
                    homeAdresses.next();
                    socialStatuses.next();
                    diagnoses.next();
                    lastVisitsDates.next();
                    firstVisitsDates.next();
                    treatments.next();
                    data.add(new Patient(ids.getString(1), fullNames.getString(1), males.getString(1),
                            datesOfBirths.getString(1), ages.getString(1), localities.getString(1),
                            homeAdresses.getString(1), socialStatuses.getString(1),
                            diagnoses.getString(1), lastVisitsDates.getString(1), firstVisitsDates.getString(1),
                            treatments.getString(1)));
                }

                fullName.setCellValueFactory(new PropertyValueFactory<Patient, String>("fullName"));
                male.setCellValueFactory(new PropertyValueFactory<Patient, String>("male"));
                dateOfBirth.setCellValueFactory(new PropertyValueFactory<Patient, String>("birthDate"));
                age.setCellValueFactory(new PropertyValueFactory<Patient, String>("age"));
                locality.setCellValueFactory(new PropertyValueFactory<Patient, String>("locality"));
                homeAdress.setCellValueFactory(new PropertyValueFactory<Patient, String>("homeAdress"));
                socialStatus.setCellValueFactory(new PropertyValueFactory<Patient, String>("socialStatus"));
                diagnos.setCellValueFactory(new PropertyValueFactory<Patient, String>("diagnos"));
                lastVisitDate.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastVisitDate"));
                firstVisitDate.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstVisitDate"));
                treatment.setCellValueFactory(new PropertyValueFactory<Patient, String>("treatment"));
                patId.setCellValueFactory(new PropertyValueFactory<Patient, String>("id"));

                myTable.setItems(data);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
