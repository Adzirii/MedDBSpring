package com.nikita.medappspringedition;

import com.nikita.medappspringedition.Beans.FXMLLoaderBean;
import com.nikita.medappspringedition.Beans.SceneComponent;
import com.nikita.medappspringedition.Configs.MyConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class MedAppSpringEditionApplication extends Application {

    public static void main(String[] args) {

        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
        FXMLLoaderBean loader = applicationContext.getBean(FXMLLoaderBean.class);
        FXMLLoader fxmlLoader1 = loader.getFXMLLoader();
        fxmlLoader1.setLocation(getClass().getResource("/com.nikita.medappspringedition/hello-view.fxml"));

        SceneComponent scene = applicationContext.getBean(SceneComponent.class);

        stage.setTitle("База медицинских данных");
        stage.setScene(scene.sceneUpdate(loader.getFXMLLoader().load()));
        stage.setResizable(false);
        stage.show();
    }




}
