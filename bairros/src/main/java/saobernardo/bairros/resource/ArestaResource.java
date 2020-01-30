package saobernardo.bairros.resource;

import java.net.URI;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
import saobernardo.bairros.domain.Aresta;
import saobernardo.bairros.dto.ArestaDTO;
import saobernardo.bairros.repository.ArestaRepository;

@RestController
@RequestMapping("/bairros")
public class ArestaResource {

	@Autowired
	private ArestaRepository caminhoRepository;
	
	@Autowired
	private ArestaDTO caminhoDTO;
	
	@GetMapping
	public List<ArestaDTO> todosOsCaminhos() {
		
		return caminhoDTO.converter(caminhoRepository.findAll());
	}
	
	@GetMapping("/{codigo_caminho}")
	public ArestaDTO UmCaminho(@PathVariable int codigo_caminho){
		
		return new ArestaDTO(caminhoRepository.findById(codigo_caminho).get());
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ArestaDTO> incluir(@RequestBody @Valid Aresta caminho, UriComponentsBuilder uriBuilder) {
		
		caminhoRepository.save(caminho);
		
		URI uri = uriBuilder.path("/bairros/{codigo_caminho}").buildAndExpand(caminho.getCodigo_caminho()).toUri();
		
		return ResponseEntity.created(uri).body(new ArestaDTO(caminho));
	}
	
	@PutMapping("/{codigo_caminho}")
	@Transactional
	public ResponseEntity<ArestaDTO> alterar(@PathVariable int codigo_caminho, @RequestBody @Valid Aresta caminho) {
		
		Aresta novoCaminho = caminhoRepository.getOne(codigo_caminho);
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
	
	@GetMapping("/teste")
	public List<Aresta> consultar(){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bairros");
			
			EntityManager em = emf.createEntityManager();
			
				List<Aresta> listaDeCaminhos = em.createQuery("select c from caminhos c", Aresta.class).getResultList();
			
			em.close();
			
		emf.close();
		
		return listaDeCaminhos;
	}
	
}
