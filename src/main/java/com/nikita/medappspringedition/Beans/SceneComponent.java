package com.nikita.medappspringedition.Beans;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SceneComponent {

    private FXMLLoaderBean loader;

    @Autowired
    public SceneComponent() throws IOException {
        Scene scene = new Scene(loader.getFXMLLoader().load());
    }


    public Scene sceneUpdate(FXMLLoader loader) throws IOException {
        return new Scene(loader.load());
    }


//    public FXMLLoaderBean getLoader() {
//        return loader;
//    }
}
