package pa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArchivoAcortandoElCamino {
	public static AcortandoElCamino leerArchivo(String path) throws FileNotFoundException {
		File archivo = new File(path);
		Scanner entrada = new Scanner(archivo);
		int cantidadDeBifurcaciones = entrada.nextInt();
		int cantidadDeGaleriasSinObstruir = entrada.nextInt();
		int cantidadDeGaleriasObstruidas = entrada.nextInt();
		AcortandoElCamino acortandoElCamino = new AcortandoElCamino(cantidadDeGaleriasSinObstruir,
				cantidadDeGaleriasObstruidas, cantidadDeBifurcaciones);

		for (int i = 0; i < cantidadDeGaleriasSinObstruir; i++) {
			acortandoElCamino.setListaDeAdyacencia(entrada.nextInt(), entrada.nextInt(), entrada.nextInt(), false);
		}

		for (int i = 0; i < cantidadDeGaleriasObstruidas; i++) {
			acortandoElCamino.setListaDeAdyacencia(entrada.nextInt(), entrada.nextInt(), entrada.nextInt(), true);
		}

		entrada.close();
		return acortandoElCamino;
	}
}
