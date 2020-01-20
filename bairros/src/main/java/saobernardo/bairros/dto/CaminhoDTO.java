package saobernardo.bairros.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import saobernardo.bairros.domain.Caminho;

@Component
public class CaminhoDTO {

	private int codigo_caminho;
	private String origem;
	private String destino;
	private double distancia;

	public CaminhoDTO(Caminho caminho) {
		super();
		this.codigo_caminho = caminho.getCodigo_caminho();
		this.origem = caminho.getOrigem();
		this.destino = caminho.getDestino();
		this.distancia = caminho.getDistancia();
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

	public List<CaminhoDTO> converter(List<Caminho> listaDeCaminhos) {
		
		return listaDeCaminhos.stream().map(CaminhoDTO::new).collect(Collectors.toList());
	}
	
}
