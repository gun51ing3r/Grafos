package saobernardo.bairros.teste;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import saobernardo.bairros.domain.Aresta;

public class TesteOracle {

	public static void main(String[] args) {
		
		Funcao funcao = new Funcao();

		//Entrada
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bairros");
		
			EntityManager em = emf.createEntityManager();
			
				List<Aresta> listaDeCaminhos = em
						.createQuery("select c from caminhos c", Aresta.class)
						.getResultList();
				
				System.out.println();
			em.close();
			
		emf.close();
		
		String origem = "D";
		String destino = "E";
		
		List<Rota> resultado = funcao.funcao(listaDeCaminhos, origem, destino);
			
			System.out.println("------------------------------------------");
			
			for(Rota rota : resultado) {
			
			System.out.println("Origem: " + rota.getRota());
			System.out.println("Destino: " + rota.getParadas());
			}
		
		}
	
}
