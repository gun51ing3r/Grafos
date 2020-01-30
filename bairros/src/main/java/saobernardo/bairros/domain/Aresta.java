package saobernardo.bairros.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.stereotype.Component;

@Entity(name = "caminhos")
@Component
public class Aresta {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int codigo_caminho;
	
	@Column(nullable = false, unique =  true)
	private String origem;
	
	@Column(nullable = false, unique = true)
	private String destino;
	
	@Column(nullable = false)
	private double distancia;
	
	public Aresta() {

	}
	
	public Aresta(int codigo_caminho, String origem, String destino, double distancia) {
		super();
		this.codigo_caminho = codigo_caminho;
		this.origem = origem;
		this.destino = destino;
		this.distancia = distancia;
	}

	public int getCodigo_caminho() {
		return codigo_caminho;
	}

	public void setCodigo_caminho(int codigo_caminho) {
		this.codigo_caminho = codigo_caminho;
	}

	public String getOrigem() {
		return origem;
	}
	
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	
	public String getDestino() {
		return destino;
	}
	
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public double getDistancia() {
		return distancia;
	}
	
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	
}
