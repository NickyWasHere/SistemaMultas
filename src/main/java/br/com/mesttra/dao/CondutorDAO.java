package br.com.mesttra.dao;

import java.util.List;

import javax.persistence.*;

import br.com.mesttra.entity.*;

public class CondutorDAO {

	private EntityManager manager;
	
	public CondutorDAO() {
		this.manager = Persistence.createEntityManagerFactory("multas").createEntityManager();
	}
	
	public void adicionarCondutor(Condutor condutor) {
		String nroCnh = condutor.getNroCnh();
		Condutor condutorCadastrado = manager.find(Condutor.class, nroCnh);
		
		if (condutorCadastrado != null) {
			System.out.println();
			System.err.println("Esta CNH já está cadastrada");
			return;
		}
		
		manager.getTransaction().begin();
		manager.persist(condutor);
		manager.getTransaction().commit();
		
	}
	
	public void removerCondutor(String nroCnh) {
		Condutor condutor = manager.find(Condutor.class, nroCnh);
		
		if (condutor == null) {
			System.out.println();
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
