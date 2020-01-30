package saobernardo.bairros.teste;

public class Rota {

	private String rota;
	private int paradas;
	
	public Rota() {
		
	}
	
	public Rota(String rota, int paradas) {
		super();
		this.rota = rota;
		this.paradas = paradas;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public int getParadas() {
		return paradas;
	}

	public void setParadas(int paradas) {
		this.paradas = paradas;
	}
	
}
