package com.nikita.medappspringedition;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class changeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField age;

    @FXML
    private Button buttonChange;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonSearch;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonBackChanges;

    @FXML
    private TextField locality;

    @FXML
    private TextField male;

    @FXML
    private TextField dateOfBirth;

    @FXML
    private TextField dateOfFirstVisit;

    @FXML
    private TextField dateOfLastVisit;

    @FXML
    private TextField diagnos;

    @FXML
    private TextField fullName;

    @FXML
    private TextField homeAdress;

    @FXML
    private TextField searchId;

    @FXML
    private TextField socialStatus;

    @FXML
    private TextField treatment;

    @FXML
    void initialize() {
        buttonBackChanges.setOnAction(event -> {
            buttonBackChanges.getScene().getWindow().hide();
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
            //stage.showAndWait();
        });

        buttonChange.setOnAction(event -> {
            String id = searchId.getText().trim();
            DatabaseHandler dbHandler = new DatabaseHandler();
            try {
                dbHandler.updatePat(fullName.getText(), male.getText(), dateOfBirth.getText(), age.getText(), socialStatus.getText(), id);
                dbHandler.updateLoc(locality.getText(), homeAdress.getText(), id);
                dbHandler.updateMedInfo(diagnos.getText(), treatment.getText(), id);
                dbHandler.updateVisits(dateOfFirstVisit.getText(), dateOfLastVisit.getText(), id);
                fullName.setText("");
                male.setText("");
                dateOfBirth.setText("");
                age.setText("");
                socialStatus.setText("");
                locality.setText("");
                homeAdress.setText("");
                diagnos.setText("");
                treatment.setText("");
                dateOfFirstVisit.setText("");
                dateOfLastVisit.setText("");
                searchId.setText("");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        buttonDelete.setOnAction(event -> {
            String id = searchId.getText().trim();
            DatabaseHandler dbHandler = new DatabaseHandler();
            try {
                dbHandler.deletePat(id);
                fullName.setText("");
                male.setText("");
                dateOfBirth.setText("");
                age.setText("");
                socialStatus.setText("");
                locality.setText("");
                homeAdress.setText("");
                diagnos.setText("");
                treatment.setText("");
                dateOfFirstVisit.setText("");
                dateOfLastVisit.setText("");
                searchId.setText("");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        buttonAdd.setOnAction(event -> {
            DatabaseHandler dbHandler = new DatabaseHandler();
            try {
                dbHandler.insertPatInf(fullName.getText(), male.getText(), dateOfBirth.getText(), age.getText(), socialStatus.getText());
                dbHandler.insertLoc(locality.getText(), homeAdress.getText());
                dbHandler.insertMedInfo(diagnos.getText(), treatment.getText());
                dbHandler.insertVisits(dateOfFirstVisit.getText(), dateOfLastVisit.getText());
                fullName.setText("");
                male.setText("");
                dateOfBirth.setText("");
                age.setText("");
                socialStatus.setText("");
                locality.setText("");
                homeAdress.setText("");
                diagnos.setText("");
                treatment.setText("");
                dateOfFirstVisit.setText("");
                dateOfLastVisit.setText("");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        buttonSearch.setOnAction(event -> {

            String id = searchId.getText().trim();
            DatabaseHandler dbHandler = new DatabaseHandler();
            try {
                ResultSet FullName = dbHandler.selectPatName(id);
                ResultSet Male = dbHandler.selectPatMale(id);
                ResultSet BDate = dbHandler.selectPatBirthDate(id);
                ResultSet Age = dbHandler.selectPatAge(id);
                ResultSet SocialStatus = dbHandler.selectPatSStatus(id);
                ResultSet Locality = dbHandler.selectPatLocality(id);
                ResultSet HomeAdress = dbHandler.selectPatHomeAdress(id);
                ResultSet Diagnos = dbHandler.selectPatDiagnos(id);
                ResultSet Treatment = dbHandler.selectPatTreatment(id);
                ResultSet LastVisit = dbHandler.selectPatLastVisit(id);
                ResultSet FirstVisit = dbHandler.selectPatFirstVisit(id);
                FullName.next();
                Male.next();
                BDate.next();
                Age.next();
                Locality.next();
                SocialStatus.next();
                HomeAdress.next();
                Diagnos.next();
                Treatment.next();
                LastVisit.next();
                FirstVisit.next();
                fullName.setText(FullName.getString(1));
                male.setText(Male.getString(1));
                dateOfBirth.setText(BDate.getString(1));
                age.setText(Age.getString(1));
                socialStatus.setText(SocialStatus.getString(1));
                locality.setText(Locality.getString(1));
                homeAdress.setText(HomeAdress.getString(1));
                diagnos.setText(Diagnos.getString(1));
                treatment.setText(Treatment.getString(1));
                dateOfLastVisit.setText(LastVisit.getString(1));
                dateOfFirstVisit.setText(FirstVisit.getString(1));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
