package Menus;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FuncionesMenuPrincipal {
    public static void salirDelPrograma() {
        System.exit(0);
    }
    
    public static void listarFicherosYDirectorios(FTPClient ftpClient, String directorioRaiz) {
        try {
            System.out.println("Directorio actual: " + ftpClient.printWorkingDirectory());
        } catch (IOException e) {
            System.err.println("No se ha podido encontrar la ruta actual");
        }

        try {
            FTPFile[] archivos = ftpClient.listFiles();
            System.out.println("Ficheros en el directorio actual: " + archivos.length);

            for (int i = 0 ; i < archivos.length ; i++) {
                System.out.println(archivos[i]);
            }
        } catch (IOException e) {
            System.err.println("No se ha podido listar los archivos");
        }
    }

    public static void subirFichero(FTPClient ftpClient, String directorioRaiz) {
        
    }

    public static void descargarFichero(FTPClient ftpClient, String directorioRaiz) {
        
    }

    public static void eliminarFichero(FTPClient ftpClient, String directorioRaiz) {
        
    }

    public static void eliminarDirectorio(FTPClient ftpClient, String directorioRaiz) {
        
    }

    public static void crearDirectorio(FTPClient ftpClient, String directorioRaiz) {
        
    }

    public static void cambiarElDirectorioActual(FTPClient ftpClient, String directorioRaiz) {
        
    }

    public static void establecerConexionRemotamente(FTPClient ftpClient, String directorioRaiz) {
        
    }
}