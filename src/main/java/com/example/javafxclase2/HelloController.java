package com.example.javafxclase2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modelo.Fichero;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

    private static final List<String> CONTINENTES =
            Arrays.asList("america", "europa", "asia", "africa", "oceania", "antartida");

    //Funcion para guardar datos
    @FXML
    protected void guardarPais() {
        String nombre = tfNombrePais.getText().trim();
        String idioma = tfIdioma.getText().trim();
        String moneda = tfMoneda.getText().trim();
        String continente = tfContinente.getText().trim().toLowerCase();
        String poblacionTexto = tfPoblacion.getText().trim();

        String mnsjError = "";
        boolean error = false;
        int poblacion = 0;

        try {
            //Compruebo si ya hay un pais con ese nombre en el fichero
            if (Fichero.existePais(nombre)) {
                mnsjError += "Ya existe un país con ese nombre\n";
                tfNombrePais.clear();
                error = true;
            }

            //Compruebo que la poblacion sea un int
            try {
                poblacion = Integer.parseInt(poblacionTexto);
            } catch (NumberFormatException e) {
                mnsjError += "La población debe ser un número entero\n";
                tfPoblacion.clear();
                error = true;
            }

            //Compruebo que el continente sea uno de los 6
            if (!CONTINENTES.contains(continente)) {
                mnsjError += "Continente incorrecto\n";
                tfContinente.clear();
                error = true;
            }

            //Si hay algun error no se guarda nada
            if (error) {
                labelError.setText(mnsjError);
                return;
            }

            //Guardo el continente con la primera letra en mayuscula
            continente = Character.toUpperCase(continente.charAt(0)) + continente.substring(1);

            Fichero.escribirFichero(nombre, poblacion, idioma, moneda, continente);
            labelError.setText("País guardado correctamente");
            limpiarCampos();
        } catch (IOException e) {
            labelError.setText("Error al acceder al fichero");
        }
    }

    private void limpiarCampos() {
        tfNombrePais.clear();
        tfPoblacion.clear();
        tfIdioma.clear();
        tfMoneda.clear();
        tfContinente.clear();
    }
}