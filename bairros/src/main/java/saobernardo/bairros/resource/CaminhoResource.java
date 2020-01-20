package saobernardo.bairros.resource;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import saobernardo.bairros.domain.Caminho;
import saobernardo.bairros.dto.CaminhoDTO;
import saobernardo.bairros.repository.CaminhoRepository;

@RestController
@RequestMapping("/bairros")
public class CaminhoResource {

	@Autowired
	private CaminhoRepository caminhoRepository;
	
	@Autowired
	private CaminhoDTO caminhoDTO;
	
	@GetMapping
	public List<CaminhoDTO> todosOsCaminhos() {
		
		return caminhoDTO.converter(caminhoRepository.findAll());
	}
	
	@GetMapping("/{codigo_caminho}")
	public CaminhoDTO UmCaminho(@PathVariable int codigo_caminho){
		
		return new CaminhoDTO(caminhoRepository.findById(codigo_caminho).get());
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<CaminhoDTO> incluir(@RequestBody @Valid Caminho caminho, UriComponentsBuilder uriBuilder) {
		
		caminhoRepository.save(caminho);
		
		URI uri = uriBuilder.path("/bairros/{codigo_caminho}").buildAndExpand(caminho.getCodigo_caminho()).toUri();
		
		return ResponseEntity.created(uri).body(new CaminhoDTO(caminho));
	}
	
	@PutMapping("/{codigo_caminho}")
	@Transactional
	public ResponseEntity<CaminhoDTO> alterar(@PathVariable int codigo_caminho, @RequestBody @Valid Caminho caminho) {
		
		Caminho novoCaminho = caminhoRepository.getOne(codigo_caminho);
			novoCaminho.setOrigem(caminho.getOrigem());
			novoCaminho.setDestino(caminho.getDestino());
			novoCaminho.setDistancia(caminho.getDistancia());
				
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{codigo_caminho}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable int codigo_caminho){
		
		caminhoRepository.deleteById(codigo_caminho);
		
		return ResponseEntity.ok().build();
	}
	
}
