package pa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class AcortandoElCamino {
	public static final int INFINITO = 99999;
	private Map<Integer, List<Arista>> listaDeAdyacencia;
	private int cantidadDeGaleriasSinObstruir;
	private int cantidadDeGaleriasObstruidas;
	private int cantidadDeBifurcaciones;
	private int bifurcacionDeInicio;
	private int[] vectorDeCostosD;
	private int[] vectorDePrecedencia;

	public AcortandoElCamino(int cantidadDeGaleriasSinObstruir, int cantidadDeGaleriasObstruidas,
			int cantidadDeBifurcaciones) {
		this.bifurcacionDeInicio = 1;
		this.cantidadDeGaleriasSinObstruir = cantidadDeGaleriasSinObstruir;
		this.cantidadDeGaleriasObstruidas = cantidadDeGaleriasObstruidas;
		this.cantidadDeBifurcaciones = cantidadDeBifurcaciones;
		this.listaDeAdyacencia = new HashMap<>();
		this.vectorDeCostosD = new int[this.cantidadDeBifurcaciones];
		vectorDePrecedencia = new int[this.cantidadDeBifurcaciones];
	}

	public void resolver() {
		this.cargarVectorDeCostosD();
		boolean[] bifurcacionesUsadas = new boolean[cantidadDeBifurcaciones];

		bifurcacionesUsadas[this.bifurcacionDeInicio - 1] = true;
		PriorityQueue<Arista> colaDePrioridad = new PriorityQueue<>();
		for (Arista a : this.listaDeAdyacencia.get(this.bifurcacionDeInicio)) {
			colaDePrioridad.add(a);
		}

		Nodo w;
		Nodo nodoAdyacenteAW;
		Arista aristaW;
		List<Arista> adyacentesW = new ArrayList<>();
		int costoDW = 0;
		int costoWI = 0;
		int costoDI = 0;

		while (!colaDePrioridad.isEmpty()) {
			aristaW = colaDePrioridad.poll();
			costoDW = aristaW.getCosto();
			w = aristaW.getDestino();
			if (bifurcacionesUsadas[w.getNumero() - 1] == false) {
				bifurcacionesUsadas[w.getNumero() - 1] = true;
				for (Arista a : this.listaDeAdyacencia.get(w.getNumero())) {
					if (bifurcacionesUsadas[a.getDestino().getNumero() - 1] == false)
						adyacentesW.add(a);
				}

				for (Arista aristasAdyacentesAW : adyacentesW) {
					nodoAdyacenteAW = aristasAdyacentesAW.getDestino();
					costoWI = aristasAdyacentesAW.getCosto();
					costoDI = vectorDeCostosD[nodoAdyacenteAW.getNumero() - 1];

					if (costoDI > costoDW + costoWI) {
						vectorDeCostosD[nodoAdyacenteAW.getNumero() - 1] = costoDW + costoWI;
						aristasAdyacentesAW.setAristaUtilizada(true);
						vectorDePrecedencia[nodoAdyacenteAW.getNumero() - 1] = w.getNumero()-1;
						colaDePrioridad.add(new Arista(new Nodo(this.bifurcacionDeInicio),
								new Nodo(nodoAdyacenteAW.getNumero()), costoDW + costoWI, false));
					}
				}
				adyacentesW.clear();
			}
		}
	}

	public void setListaDeAdyacencia(int o, int d, int costo, boolean aristaObstruida) {
		Arista a = new Arista(new Nodo(o), new Nodo(d), costo, aristaObstruida);
		if (!this.listaDeAdyacencia.containsKey(a.getOrigen().getNumero())) {
			this.listaDeAdyacencia.put(a.getOrigen().getNumero(), new ArrayList<Arista>());
		}
		this.listaDeAdyacencia.get(a.getOrigen().getNumero()).add(a);

		if (!this.listaDeAdyacencia.containsKey(a.getDestino().getNumero())) {
			this.listaDeAdyacencia.put(a.getDestino().getNumero(), new ArrayList<Arista>());
		}
		this.listaDeAdyacencia.get(a.getDestino().getNumero()).add(new Arista(new Nodo(a.getDestino().getNumero()),
				new Nodo(a.getOrigen().getNumero()), a.getCosto(), aristaObstruida));
	}

	public void cargarVectorDeCostosD() {
		this.vectorDeCostosD[this.bifurcacionDeInicio - 1] = 0;

		for (Arista a : this.listaDeAdyacencia.get(this.bifurcacionDeInicio)) {
			if (!a.isAristaObstruida()) {
				this.vectorDeCostosD[a.getDestino().getNumero() - 1] = a.getCosto();
			}
		}

		for (int i = 1; i < vectorDeCostosD.length; i++) {
			if (this.vectorDeCostosD[i] == 0) {
				this.vectorDeCostosD[i] = AcortandoElCamino.INFINITO;
			}
		}
	}

	public int[] getVectorDeCostosD() {
		return vectorDeCostosD;
	}

	public int[] getVectorDePrecedencia() {
		return vectorDePrecedencia;
	}
}
