package br.com.mesttra.dao;

import java.util.List;

import javax.persistence.*;

import br.com.mesttra.entity.Condutor;

public class CondutorDAO {

	private EntityManager manager;
	
	public CondutorDAO() {
		this.manager = Persistence.createEntityManagerFactory("multas").createEntityManager();
	}
	
	public void adicionarCondutor(Condutor condutor) {
		manager.getTransaction().begin();
		manager.persist(condutor);
		manager.getTransaction().commit();
		
	}
	
	public void removerCondutor(String nroCnh) {
		Condutor condutor = manager.find(Condutor.class, nroCnh);
		
		if (condutor == null) {
			System.err.println("Nenhum condutor encontrado");
			return;
		}
		
		manager.getTransaction().begin();
		manager.remove(condutor);
		manager.getTransaction().commit();
		
	}
	
	public Condutor verCondutor(String nroCnh) {
		return manager.find(Condutor.class, nroCnh);
		
	}
	
	public List<Condutor> listarCondutores() {
		Query query = manager.createQuery("SELECT c FROM Condutor as c");
		return query.getResultList();
		
	}
}
