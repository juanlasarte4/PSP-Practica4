package Menus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FuncionesMenuPrincipal {

    public static void salirDelPrograma() {
        System.exit(0);
    }

    public static void listarArchivosYDirectorios(FTPClient ftpClient, String directorioRaiz) {
        try {
            System.out.println("Directorio actual: " + ftpClient.printWorkingDirectory());
        } catch (IOException ioe) {
            System.err.println("No se ha podido encontrar la ruta actual");
        }

        try {
            FTPFile[] archivos = ftpClient.listFiles();
            System.out.println("Ficheros en el directorio actual: " + archivos.length);

            for (int i = 0; i < archivos.length; i++) {
                System.out.println(archivos[i]);
            }
        } catch (IOException ioe) {
            System.err.println("No se ha podido listar los archivos");
        }
    }

    public static void subirArchivo(FTPClient ftpClient, String directorioRaiz) {

        JFileChooser selectorArchivos = new JFileChooser();
        selectorArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        // APPROVE_OPTION - CANCEL_OPTION - ERROR_OPTION
        int resultado = selectorArchivos.showDialog(selectorArchivos, "Subir");

        File archivoParaSubir = selectorArchivos.getSelectedFile();
        if ((archivoParaSubir == null) || (archivoParaSubir.getName().equals(""))) {
            JOptionPane.showMessageDialog(selectorArchivos, "Nombre de archivo incorrecto",
                    "Nombre de archivo incorrecto", JOptionPane.ERROR_MESSAGE);
        }

        String nombreArchivoParaSubir = archivoParaSubir.getName();
        InputStream is = null;
        try {
            is = new FileInputStream(archivoParaSubir);
        } catch (FileNotFoundException fnfe) {
            System.err.println("No se ha podido encontrar el archivo para subir");
        }

        try {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (IOException ioe) {
            System.err.println("No se ha podido actualizar el formato");
        }

        try {
            ftpClient.storeFile(nombreArchivoParaSubir, is);
        } catch (IOException ioe) {
            System.err.println("No se ha podido subir el archivo al servidor");
        }
    }

    public static void descargarArchivo(FTPClient ftpClient, String directorioRaiz) {
        JFileChooser selectorArchivos = new JFileChooser();
        selectorArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        selectorArchivos.showDialog(selectorArchivos, "Descargar");

        File archivoParaDescargar = selectorArchivos.getSelectedFile();
        if ((archivoParaDescargar == null) || (archivoParaDescargar.getName().equals(""))) {
            JOptionPane.showMessageDialog(selectorArchivos, "Nombre de archivo incorrecto",
                    "Nombre de archivo incorrecto", JOptionPane.ERROR_MESSAGE);
        }

        OutputStream os = null;
        try {
            os = new FileOutputStream("C:/Users/Juan/Downloads/" + archivoParaDescargar.getName());
        } catch (FileNotFoundException fnfe) {
            System.err.println("No se ha podido encontrar el archivo para descargarlo");
        }

        try {
            boolean estadoDescarga = ftpClient.retrieveFile(archivoParaDescargar.getName(), os);
            if (estadoDescarga) {
                System.out.println("Descarga completada con exito.");
            } else {
                System.out.println("No se pudo descargar el archivo");
            }
        } catch (IOException ioe) {
            System.err.println("No se ha podido descargar el archivo del servidor");
        }
    }

    public static void eliminarArchivo(FTPClient ftpClient, String directorioRaiz) {
        JFileChooser selectorArchivos = new JFileChooser();
        selectorArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        selectorArchivos.showDialog(selectorArchivos, "Eliminar");

        File archivoParaBorrar = selectorArchivos.getSelectedFile();
        if ((archivoParaBorrar == null) || (archivoParaBorrar.getName().equals(""))) {
            JOptionPane.showMessageDialog(selectorArchivos, "Nombre de archivo incorrecto",
                    "Nombre de archivo incorrecto", JOptionPane.ERROR_MESSAGE);
        }

        try {
            ftpClient.dele(archivoParaBorrar.getName());
        } catch (IOException e) {
            System.err.println("No se ha podido borrar el archivo del servidor");
        }

    }

    public static void eliminarDirectorio(FTPClient ftpClient, String directorioRaiz) {
        JFileChooser selectorDirectorio = new JFileChooser();
        selectorDirectorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        selectorDirectorio.showDialog(selectorDirectorio, "Eliminar");

        File directorioParaBorrar = selectorDirectorio.getSelectedFile();
        if ((directorioParaBorrar == null) || (directorioParaBorrar.getName().equals(""))) {
            JOptionPane.showMessageDialog(selectorDirectorio, "Nombre de directorio incorrecto",
                    "Nombre de directorio incorrecto", JOptionPane.ERROR_MESSAGE);
        }

        try {
            ftpClient.removeDirectory(directorioParaBorrar.getName());
        } catch (IOException e) {
            System.err.println("No se ha podido borrar el directorio del servidor");
        }
    }

    public static void crearDirectorio(FTPClient ftpClient, String directorioRaiz) {
        JFileChooser selectorDirectorio = new JFileChooser();
        selectorDirectorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        selectorDirectorio.showDialog(selectorDirectorio, "Crear");

        File directorioParaCrear = selectorDirectorio.getSelectedFile();
        if ((directorioParaCrear == null) || (directorioParaCrear.getName().equals(""))) {
            JOptionPane.showMessageDialog(selectorDirectorio, "Nombre de directorio incorrecto",
                    "Nombre de directorio incorrecto", JOptionPane.ERROR_MESSAGE);
        }

        try {
            ftpClient.makeDirectory(directorioParaCrear.getName());
        } catch (IOException e) {
            System.err.println("No se ha podido crear el directorio en el servidor");
        }
    }

    public static void cambiarElDirectorioActual(FTPClient ftpClient, String directorioRaiz) {
        JFileChooser selectorDirectorio = new JFileChooser();
        selectorDirectorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        selectorDirectorio.showDialog(selectorDirectorio, "Cambiar");

        File directorioParaCambiar = selectorDirectorio.getSelectedFile();
        if ((directorioParaCambiar == null) || (directorioParaCambiar.getName().equals(""))) {
            JOptionPane.showMessageDialog(selectorDirectorio, "Nombre de directorio incorrecto",
                    "Nombre de directorio incorrecto", JOptionPane.ERROR_MESSAGE);
        }

        try {
            ftpClient.changeWorkingDirectory(directorioParaCambiar.getName());
            directorioRaiz = directorioParaCambiar.getName();
        } catch (IOException e) {
            System.err.println("No se ha podido crear el directorio en el servidor");
        }
    }
}