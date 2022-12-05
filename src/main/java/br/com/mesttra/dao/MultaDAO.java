package br.com.mesttra.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import br.com.mesttra.entity.*;

public class MultaDAO {

	private EntityManager manager;
	
	public MultaDAO() {
		this.manager = Persistence.createEntityManagerFactory("multas").createEntityManager();
		
	}
	
	public void adicionarMulta(Multa multa, Veiculo veiculo, Condutor condutor) {	
		List<Multa> multas = veiculo.getMultas();
		if (multas == null || multas.isEmpty()) {
			multas = new ArrayList<Multa>();
		}
		
		multas.add(multa);
		veiculo.setMultas(multas);
		
		multa.setVeiculo(veiculo);
		condutor.setPontuacao(condutor.getPontuacao() + multa.getPontuacao());
		
		manager.getTransaction().begin();
		manager.persist(multa);
		manager.merge(veiculo);
		manager.merge(condutor);
		manager.getTransaction().commit();
		
	}
	
	public void removerMulta(int codigoMulta) {
		Multa multa = manager.find(Multa.class, codigoMulta);
		
		if (multa == null) {
			System.err.println("Nenhuma multa encontrada");
			return;
		}
		
		manager.getTransaction().begin();
		manager.remove(multa);
		manager.getTransaction().commit();
		
	}
	
	public Multa verMulta(int codigoMulta) {
		return manager.find(Multa.class, codigoMulta);
		
	}
	
	public List<Multa> listarMultas() {
		Query query = manager.createQuery("SELECT m FROM Multa as m");
		return query.getResultList();
		
	}
}
