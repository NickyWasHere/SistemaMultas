package br.com.mesttra.dao;

import java.util.List;

import javax.persistence.*;

import br.com.mesttra.entity.Condutor;
import br.com.mesttra.entity.Veiculo;

public class VeiculoDAO {

	private EntityManager manager;
	
	public VeiculoDAO() {
		this.manager = Persistence.createEntityManagerFactory("multas").createEntityManager();
		
	}
	
	public void adicionarVeiculo(Veiculo veiculo, Condutor condutor) {
		manager.getTransaction().begin();
		manager.persist(veiculo);
		manager.merge(condutor);
		manager.getTransaction().commit();
		
	}
	
	public void removerVeiculo(String placa) {
		Veiculo veiculo = manager.find(Veiculo.class, placa);
		
		if (veiculo == null) {
			System.err.println("Nenhum veículo encontrado");
			return;
		}
		
		manager.getTransaction().begin();
		manager.remove(veiculo);
		manager.getTransaction().commit();
		
	}
	
	public Veiculo verVeiculo(String placa) {
		return manager.find(Veiculo.class, placa);
		
	}
	
	public List<Veiculo> listarVeiculos() {
		Query query = manager.createQuery("SELECT v FROM Veiculo as v");
		return query.getResultList();
		
	}
}
