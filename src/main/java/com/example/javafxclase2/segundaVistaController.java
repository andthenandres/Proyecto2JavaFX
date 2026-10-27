package com.example.javafxclase2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import modelo.Fichero;

import java.io.IOException;

public class segundaVistaController {

    @FXML
    ComboBox<String> comboBox;
    @FXML
    TextArea textArea;

    @FXML
    public void initialize() {
        comboBox.getItems().addAll("España", "Portugal", "Taiwan", "Belgica", "Francia");

        //Dejo a francia sin rellenar para que muestre null
        try {
            Fichero.altaSiNoExiste("España",   48000000, "Español",   "Euro", "Europa");
            Fichero.altaSiNoExiste("Portugal", 10400000, "Portugués", "Euro", "Europa");
            Fichero.altaSiNoExiste("Taiwan",   23400000, "Mandarín",  "Nuevo dólar taiwanés", "Asia");
            Fichero.altaSiNoExiste("Belgica",  11800000, "Neerlandés/Francés", "Euro", "Europa");
        } catch (IOException e) {
            textArea.setText("No se pudo preparar el fichero: " + e.getMessage());
        }
    }

    @FXML
    public void cambiarValor(ActionEvent actionEvent) {
        String valor = comboBox.getValue();
        if (valor == null) {
            return;
        }

        try {
            String[] datos = Fichero.buscarPais(valor);

            if (datos == null) {
                textArea.setText("El país \"" + valor + "\" no existe en el fichero.");
            } else {
                textArea.setText(
                        "Nombre: "     + datos[0] + "\n" +
                                "Población: "  + datos[1] + "\n" +
                                "Idioma: "     + datos[2] + "\n" +
                                "Moneda: "     + datos[3] + "\n" +
                                "Continente: " + datos[4]);
            }
        } catch (IOException e) {
            textArea.setText("Error al leer el fichero: " + e.getMessage());
        }
    }
}