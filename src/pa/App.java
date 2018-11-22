package pa;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class App {
	public static void main(String[] args) {
		try {
			AcortandoElCamino acortadoElCamino = ArchivoAcortandoElCamino.leerArchivo("resource\\ciudad.in");
			acortadoElCamino.resolver();
			System.out.println(Arrays.toString(acortadoElCamino.getVectorDeCostosD()));
			//System.out.println(Arrays.toString(acortadoElCamino.getVectorDePrecedencia()));
						for (int i = 0; i < acortadoElCamino.getVectorDePrecedencia().length; i++) {
				System.out.println("Al nodo " + (i+1) + " loprecede el nodo " + (acortadoElCamino.getVectorDePrecedencia()[i]+1) + " ");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
