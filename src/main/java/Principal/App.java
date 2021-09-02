package Principal;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import utilidades.Leer;

public class App {

	static final int PUERTO = 14147;
	static final String SERVER = "localhost";
	static final String USUARIO = "juuann04";
	static final String CLAVE = "1234";

	public static void main(String[] args) {
		FTPClient ftpClient = new FTPClient();
		int opcion = 1;
		String directorioRaiz = null;

		try {
			ftpClient.connect(SERVER);
		} catch (IOException ioe) {
			System.err.println("No se ha podido conectar al servidor");
		}

		if (ftpClient.getReplyCode() == 220) {
			System.out.println("El servidor FTP está preparado");
		} else {
			System.err.println("El servidor FTP esta apagado");
		}

		try {
			boolean estaLogueado = ftpClient.login(USUARIO, CLAVE);

			if (estaLogueado) {
				System.out.println("Login correcto");
			} else {
				System.out.println("Login incorrecto");
			}
		} catch (IOException ioe) {
			System.err.println("No se ha podido comprobar si el usuario esta dado de alta");
		} catch (NullPointerException npe) {
			System.err.println("No se ha recibido respuesta del servidor");
		}

		while (opcion != 0) {
			Menus.MenuPrincipal.imprimirMenuPrincipal();
			opcion = Leer.pedirEnteroValidar();
			switch (opcion) {
				case 0:
					Menus.FuncionesMenuPrincipal.salirDelPrograma();
					break;
				case 1:
					Menus.FuncionesMenuPrincipal.listarFicherosYDirectorios(ftpClient, directorioRaiz);
					break;
				case 2:
					Menus.FuncionesMenuPrincipal.subirFichero(ftpClient, directorioRaiz);
					break;
				case 3:
					Menus.FuncionesMenuPrincipal.descargarFichero(ftpClient, directorioRaiz);
					break;
				case 4:
					Menus.FuncionesMenuPrincipal.eliminarFichero(ftpClient, directorioRaiz);
					break;
				case 5:
					Menus.FuncionesMenuPrincipal.eliminarDirectorio(ftpClient, directorioRaiz);
					break;
				case 6:
					Menus.FuncionesMenuPrincipal.crearDirectorio(ftpClient, directorioRaiz);
					break;
				case 7:
					Menus.FuncionesMenuPrincipal.cambiarElDirectorioActual(ftpClient, directorioRaiz);
					break;
				case 8:
					Menus.FuncionesMenuPrincipal.establecerConexionRemotamente(ftpClient, directorioRaiz);
					break;
				default:
					System.err.println("Opcion incorrecta");
					break;
			}
		}
	}
}