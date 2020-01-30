package saobernardo.bairros.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saobernardo.bairros.domain.Aresta;
import saobernardo.bairros.dto.ArestaDTO;
import saobernardo.bairros.repository.ArestaRepository;

@RestController
@RequestMapping("/bairros/graph")
public class ArestaService {
	
	@Autowired
	private ArestaRepository caminhoRepository;
	
	@GetMapping
	public String teste() {
		
		return "Escolha sua opção!";
	}
	
	@GetMapping("/{origem}/ate/{destino}")
	public List<ArestaDTO> DeAte(@PathVariable String origem, @PathVariable String destino) {
		
		List<Aresta> resultado = new ArrayList<Aresta>();
		
		List<Aresta> listaDeCaminhos = caminhoRepository.findAll();
		
		for(Aresta caminho: listaDeCaminhos) {
			
			if(caminho.getOrigem().equals(origem) && caminho.getDestino().equals(destino)) {
				
				resultado.add(caminho);
			}
			
		}
		
		return resultado.stream().map(ArestaDTO::new).collect(Collectors.toList());		
	}

}
