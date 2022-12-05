package br.com.mesttra.dao;

import java.util.List;

import javax.persistence.*;

import br.com.mesttra.entity.Condutor;
import br.com.mesttra.entity.Multa;
import br.com.mesttra.entity.Veiculo;

public class MultaDAO {

	private EntityManager manager;
	
	public MultaDAO() {
		this.manager = Persistence.createEntityManagerFactory("multas").createEntityManager();
		
	}
	
	public void adicionarMulta(Multa multa, Veiculo veiculo, Condutor condutor) {		
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
