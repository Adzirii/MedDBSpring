package com.nikita.medappspringedition;

import com.nikita.medappspringedition.Beans.FXMLLoaderBean;
import com.nikita.medappspringedition.Beans.SceneComponent;
import com.nikita.medappspringedition.Configs.MyConfig;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DBcontroller {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonEnter;

    @FXML
    private Button buttonChange;

    @FXML
    void initialize() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class);
        FXMLLoaderBean loader = ctx.getBean(FXMLLoaderBean.class);
        SceneComponent sceneComponent = ctx.getBean(SceneComponent.class);

        buttonEnter.setOnAction(event -> {
            buttonEnter.getScene().getWindow().hide();

            loader.changeUrl(getClass().getResource("/com.nikita.medappspringedition/logIN.fxml"));

            try {
                loader.getFXMLLoader().load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getFXMLLoader().getRoot();
            Stage stage = new Stage();
            try {
                stage.setScene(sceneComponent.sceneUpdate(loader.getFXMLLoader().load()));
            } catch (IOException e) {
            }
            stage.showAndWait();
        });

        buttonChange.setOnAction(event -> {
            buttonChange.getScene().getWindow().hide();
            loader.changeUrl(getClass().getResource("/com.nikita.medappspringedition/changes.fxml"));

            try {
                loader.getFXMLLoader().load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getFXMLLoader().getRoot();
            Stage stage = new Stage();
            try {
                stage.setScene(sceneComponent.sceneUpdate(loader.getFXMLLoader().load()));
            } catch (IOException e) {
            }
            stage.showAndWait();
        });
    }

}