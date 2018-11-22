package pa;

public class Arista implements Comparable<Arista>{
	private Nodo nodoOrigen;
	private Nodo nodoDestino;
	private int costo;
	private boolean aristaUtilizada = false;
	private boolean aristaObstruida;

	public Arista(Nodo nodoOrigen, Nodo nodoDestino, int costo,boolean aristaObstruida) {
		this.nodoOrigen = nodoOrigen;
		this.nodoDestino = nodoDestino;
		this.costo = costo;
		this.aristaObstruida = aristaObstruida;
	}

	public Nodo getOrigen() {
		return nodoOrigen;
	}

	public Nodo getDestino() {
		return nodoDestino;
	}

	public int getCosto() {
		return costo;
	}

	public boolean isAristaUtilizada() {
		return aristaUtilizada;
	}

	public void setAristaUtilizada(boolean aristaUtilizada) {
		this.aristaUtilizada = aristaUtilizada;
	}

	public boolean isAristaObstruida() {
		return aristaObstruida;
	}

	@Override
	public int compareTo(Arista a2) {
		return this.getCosto() - a2.getCosto();
	}
}
