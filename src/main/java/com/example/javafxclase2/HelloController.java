package com.example.javafxclase2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Locale;

import static modelo.Fichero.ruta;

public class HelloController {
    @FXML
    private TextField tfNombrePais;
    @FXML
    private TextField tfPoblacion;
    @FXML
    private TextField tfIdioma;
    @FXML
    private TextField tfMoneda;
    @FXML
    private TextField tfContinente;
    @FXML
    private Label labelError;
    @FXML
    private Button buttonGuardar;

    //En esta funcion voy a validar el textField de continente y que la poblacion sea un numero
    public void comprobarDatos(){
        String mnsjError= "";
        int poblacion = 0;
        String continente = "";
        boolean error = false;
        String [] continentes = new String [] {"america","europa","africa","asia","oceania","Antartida"};

        //Paso el texto a la var sin espacios y en minus
        continente = tfContinente.getText().trim().toLowerCase(Locale.ROOT);

        //Compruebo que el txtF de continente tenga uno de los 6 continentes indicados
        if(!Arrays.asList(continentes).contains(continente)){
              error = true;
              mnsjError += "El continente es incorrecto\n";
        }
        try{
            poblacion = Integer.parseInt(tfPoblacion.getText().trim());
        }catch (InputMismatchException e){
            mnsjError += "La poblacion no es un numero\n";
            error = true;
        }
        labelError.setText(mnsjError);
        }

        public boolean comprobarPais() throws FileNotFoundException {
            FileInputStream fis = new FileInputStream(ruta);
            DataInputStream dos = new DataInputStream(fis);

            String pais = "";

            pais = tfNombrePais.getText().trim().toLowerCase(Locale.ROOT);


        }
}




