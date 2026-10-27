package modelo;

import java.io.*;

public class Fichero {
    public static String ruta = "paises.bin";

    public static void escribirFichero(String nombreP, int poblacion, String idioma,
                                       String moneda, String continente) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ruta, true))) {
            dos.writeUTF(nombreP);
            dos.writeInt(poblacion);
            dos.writeUTF(idioma);
            dos.writeUTF(moneda);
            dos.writeUTF(continente);
        }
    }

    public static boolean existePais(String nombre) throws IOException {
        return buscarPais(nombre) != null;
    }

    // Devuelve {nombre, poblacion, idioma, moneda, continente} o null si no existe
    public static String[] buscarPais(String nombre) throws IOException {
        File f = new File(ruta);
        if (!f.exists()) {
            return null;
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
            while (true) {
                String nombreLeido = dis.readUTF();
                int poblacion = dis.readInt();
                String idioma = dis.readUTF();
                String moneda = dis.readUTF();
                String continente = dis.readUTF();

                if (nombreLeido.equalsIgnoreCase(nombre)) {
                    return new String[]{nombreLeido, String.valueOf(poblacion),
                            idioma, moneda, continente};
                }
            }
        } catch (EOFException e) {
            return null; // fin del fichero sin encontrarlo
        }
    }

    // Da de alta un país solo si no estaba ya
    public static void altaSiNoExiste(String nombre, int poblacion, String idioma,
                                      String moneda, String continente) throws IOException {
        if (!existePais(nombre)) {
            escribirFichero(nombre, poblacion, idioma, moneda, continente);
        }
    }
}