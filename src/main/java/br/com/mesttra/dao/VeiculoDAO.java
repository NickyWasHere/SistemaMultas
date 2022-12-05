package br.com.mesttra.dao;

import java.util.List;

import javax.persistence.*;

import br.com.mesttra.entity.*;

public class VeiculoDAO {

	private EntityManager manager;
	
	public VeiculoDAO() {
		this.manager = Persistence.createEntityManagerFactory("multas").createEntityManager();
		
	}
	
	public void adicionarVeiculo(Veiculo veiculo, Condutor condutor) {
		veiculo.setCondutor(condutor);
		condutor.setVeiculo(veiculo);
		
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
		
		Condutor condutor = veiculo.getCondutor();
		condutor.setVeiculo(null);
		
		manager.getTransaction().begin();
		manager.remove(veiculo);
		manager.merge(condutor);
		manager.getTransaction().commit();
		
	}
	
	public Veiculo verVeiculo(String placa) {
		return manager.find(Veiculo.class, placa);
		
	}
	
	public List<Veiculo> listarVeiculos() {
		Query query = manager.createQuery("SELECT v FROM Veiculo as v");
		return query.getResultList();
		
	}
	
	public void venderVeiculo(Condutor vendedor, Condutor comprador, Veiculo veiculo) {	
		veiculo.setCondutor(comprador);
		vendedor.setVeiculo(null);
		
		if (comprador.getVeiculo() != null) {
			String placaAntiga = comprador.getVeiculo().getPlaca();
			Veiculo veiculoAntigo = manager.find(Veiculo.class, placaAntiga);
			
			comprador.setVeiculo(veiculo);
			
			manager.getTransaction().begin();
			manager.merge(vendedor);
			manager.merge(comprador);
			manager.merge(veiculo);
			manager.remove(veiculoAntigo);
			manager.getTransaction().commit();
			
		} else {
			comprador.setVeiculo(veiculo);
			
			manager.getTransaction().begin();
			manager.merge(vendedor);
			manager.merge(comprador);
			manager.merge(veiculo);
			manager.getTransaction().commit();
		}
		
	}
}
