package saobernardo.bairros.teste;

import java.util.ArrayList;
import java.util.List;
import saobernardo.bairros.domain.Aresta;

public class Funcao {

	public List<Rota> funcao(List<Aresta> listaDeArestas, String origem, String destino){
		
		List<Rota> resultado = new ArrayList<Rota>();
		
		//Lista dos caminhos com origem A
		for(Aresta aresta : listaDeArestas) {

			List<Aresta> origens =  new ArrayList<Aresta>();
			
			if(aresta.getOrigem().equals(origem)) {				
			
				origens.add(aresta);
				listaDeArestas.remove(aresta);
			}
			
		}
		
		for(int i = 0; i < resultado.size()-1; i++) {
			
			Rota rota = resultado.get(resultado.size()-1);
			String novaOrigem = rota.getRota().substring(rota.getRota().length()-1);
			
			if(!rota.getRota().endsWith(destino)) {
				
				if(rota.getRota().endsWith(origem)) {
					
					resultado.remove(i);
				}
				else {
					
					this.funcao(listaDeArestas, novaOrigem, destino);
				}
					
			}
			
		}

		return resultado;
	}
	
	public List<Aresta> proximosNo(List<Aresta> listaDeArestas, String origem) {
		
		List<Aresta> proximos = new ArrayList<Aresta>();
		
		for(Aresta aresta : listaDeArestas) {
			
			if(aresta.getOrigem().equals(origem))
				proximos.add(aresta);				
		}
		
		return proximos;
	}

}