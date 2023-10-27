package com.nikita.medappspringedition.Beans;

import javafx.fxml.FXMLLoader;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class FXMLLoaderBean {

    private final FXMLLoader loader = new FXMLLoader();

    public FXMLLoader getFXMLLoader(){
        return loader;
    }

    public void changeUrl(URL url){
        loader.setLocation(url);
    }
}
