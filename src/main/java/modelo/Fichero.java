package modelo;

import java.io.*;

public class Fichero {
    //Pongo la ruta en static y publica para que sea accesible desde otros paises
    public static String ruta ="paises.dat";
    public static void escribirFichero() throws IOException {

        FileOutputStream fos = new FileOutputStream(ruta, true);
        DataOutputStream dos = new DataOutputStream(fos);

        String nombreP = "";
        int poblacion = 0;
        String idioma = "";
        String moneda = "";
        String continente = "";


        dos.writeUTF(nombreP);
        dos.writeInt(poblacion);
        dos.writeUTF(idioma);
        dos.writeUTF(moneda);
        dos.writeUTF(continente);

        //Cerramos todo
        fos.close();
        dos.close();
    }
    public void leerFichero() throws IOException {
        FileInputStream fis = new FileInputStream(ruta);
        DataInputStream dis = new DataInputStream(fis);

        String nombreP = "";
        int poblacion = 0;
        String idioma = "";
        String moneda = "";
        String continente = "";



        //Escribo los valores mientras haya valores escritos
        while(true) {
            try {

                nombreP = dis.readUTF();
                poblacion = dis.readInt();
                idioma = dis.readUTF();
                moneda = dis.readUTF();
                continente = dis.readUTF();
            }catch (IOException e){
                System.out.println("Fin de la lista");
                break;
            }
        }

        String datos [] = {nombreP, poblacion, idioma, moneda, continente};

        //Cerramos
        fis.close();
        dis.close();
    }
}
