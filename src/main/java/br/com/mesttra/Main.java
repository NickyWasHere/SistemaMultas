package br.com.mesttra;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.mesttra.dao.*;
import br.com.mesttra.entity.*;
import br.com.mesttra.ui.*;

public class Main {

	public static Scanner sc = new Scanner(System.in);
	
	private static CondutorDAO condutorDAO = new CondutorDAO();
	private static VeiculoDAO veiculoDAO = new VeiculoDAO();
	private static MultaDAO multaDAO = new MultaDAO();
	
	public static void main(String[] args) {
		while (true) {
			int resp = inicio();
			
			switch (resp) {
			case 1:
				condutor();
				break;
				
			case 2:
				veiculo();
				break;
				
			case 3:
				multa();
				break;
				
			case 4:
				sc.close();
				System.err.println("Programa encerrado");
				System.exit(0);
				break;
				
			default:
				System.err.println("Opção inválida");
				break;
			}
		}
	}
	
	private static void condutor() {
		while (true) {
			CondutorUI.menu();
			int resp = sc.nextInt();
			
			switch (resp) {
			case 1:
				cadastrarCondutor();
				break;
				
			case 2:
				removerCondutor();
				break;
				
			case 3:
				consultarCondutor();
				break;
				
			case 4:
				listarCondutores();
				break;
				
			case 5:
				return;
				
			default:
				System.err.println("Opção inválida");
				break;
			}
		}
	}

	private static void cadastrarCondutor() {
		System.out.println();
		System.out.println("Cadastrar condutor");
		System.out.println();
		
		Condutor condutor = CondutorUI.cadastrar(sc);
		condutorDAO.adicionarCondutor(condutor);
	}

	private static void removerCondutor() {
		System.out.println();
		System.out.println("Remover condutor");
		System.out.println();
		
		System.out.print("Número da CNH: ");
		String cnh = sc.next();
		
		condutorDAO.removerCondutor(cnh);
	}

	private static void consultarCondutor() {
		System.out.println();
		System.out.println("Consultar condutor");
		System.out.println();
		
		System.out.print("Número da CNH: ");
		String cnh = sc.next();
		
		Condutor condutor = condutorDAO.verCondutor(cnh);
		if (condutor == null) {
			System.err.println("Nenhum condutor encontrado");
		} else {
			System.out.println(condutor);
		}
	}

	private static void listarCondutores() {
		System.out.println();
		System.out.println("Listar condutores");
		System.out.println();
		
		List<Condutor> condutores = condutorDAO.listarCondutores();
		if (condutores.isEmpty()) {
			System.err.println("Nenhum condutor encontrado");
			
		} else {
			for (Condutor c : condutores) {
				System.out.println(c);
			}
			
		}
	}

	private static void veiculo() {		
		while (true) {
			VeiculoUI.menu();
			int resp = sc.nextInt();
			
			switch (resp) {
			case 1:
				cadastrarVeiculo();
				break;
				
			case 2:
				removerVeiculo();
				break;
				
			case 3:
				consultarVeiculo();
				break;
				
			case 4:
				listarVeiculos();
				break;
				
			case 5:
				return;
				
			default:
				System.err.println("Opção inválida");
				break;
			}
		}
	}

	private static void cadastrarVeiculo() {
		System.out.println();
		System.out.println("Cadastrar veículo");
		System.out.println();
		
		System.out.print("CNH do condutor: ");
		String cnh = sc.next();
		
		Condutor condutor = condutorDAO.verCondutor(cnh);
		if (condutor == null) {
			System.err.println("Este condutor não está cadastrado");
			
		} else {
			Veiculo veiculo = VeiculoUI.cadastrar(sc);
			veiculo.setCondutor(condutor);
			condutor.setVeiculo(veiculo);
			veiculoDAO.adicionarVeiculo(veiculo, condutor);
			
		}
	}

	private static void removerVeiculo() {
		System.out.println();
		System.out.println("Remover veículo");
		System.out.println();
		
		System.out.print("Placa: ");
		String placa = sc.next();
		
		veiculoDAO.removerVeiculo(placa);
	}

	private static void consultarVeiculo() {
		System.out.println();
		System.out.println("Consultar veículo");
		System.out.println();
		
		System.out.print("Placa: ");
		String placa = sc.next();
		
		Veiculo veiculo = veiculoDAO.verVeiculo(placa);
		if (veiculo == null) {
			System.err.println("Nenhum veículo encontrado");
		} else {
			System.out.println(veiculo);
		}
	}

	private static void listarVeiculos() {
		System.out.println();
		System.out.println("Listar veículos");
		System.out.println();
		
		List<Veiculo> veiculos = veiculoDAO.listarVeiculos();
		
		if (veiculos.isEmpty()) {
			System.err.println("Nenhum veículo encontrado");
			
		} else {
			for (Veiculo v : veiculos) {
				System.out.println(v);
			}
			
		}
	}
	
	private static void multa() {
		while (true) {
			MultaUI.menu();
			int resp = sc.nextInt();
			
			switch (resp) {
			case 1:
				cadastrarMulta();
				break;
				
			case 2:
				removerMulta();
				break;
				
			case 3:
				consultarMulta();
				break;
				
			case 4:
				listarMultasVeiculo();
				break;
				
			case 5:
				listarMultas();
				break;
				
			case 6:
				return;
				
			default:
				System.err.println("Opção inválida");
				break;
			}
		}
	}

	private static void cadastrarMulta() {
		System.out.println();
		System.out.println("Cadastrar multa");
		System.out.println();
		
		System.out.print("Placa do veículo: ");
		String placa = sc.next();
		
		Veiculo veiculo = veiculoDAO.verVeiculo(placa);
		if (veiculo == null) {
			System.err.println("Este veículo não está cadastrado");
			
		} else {
			Multa multa = MultaUI.cadastrar(sc);
			Condutor condutor = condutorDAO.verCondutor(veiculo.getCondutor().getNroCnh());
			
			List<Multa> multas = veiculo.getMultas();
			if (multas.isEmpty() || multas == null) {
				multas = new ArrayList<Multa>();
			}
			
			multas.add(multa);
			veiculo.setMultas(multas);
			
			multa.setVeiculo(veiculo);
			condutor.setPontuacao(condutor.getPontuacao() + multa.getPontuacao());
			
			multaDAO.adicionarMulta(multa, veiculo, condutor);
			
		}
	}

	private static void removerMulta() {
		System.out.println();
		System.out.println("Remover multa");
		System.out.println();
		
		System.out.print("Código ");
		int codigoMulta = sc.nextInt();
		
		multaDAO.removerMulta(codigoMulta);
	}

	private static void consultarMulta() {
		System.out.println();
		System.out.println("Consultar multa");
		System.out.println();
		
		System.out.print("Código: ");
		int codigoMulta = sc.nextInt();
		
		Multa multa = multaDAO.verMulta(codigoMulta);
		if (multa == null) {
			System.err.println("Nenhuma multa encontrada");
			
		} else {
			System.out.println(multa);
		}
	}
	
	private static void listarMultasVeiculo() {
		System.out.println();
		System.out.println("Listar multas de um veículo");
		System.out.println();
		
		System.out.print("Placa: ");
		String placa = sc.next();
		
		Veiculo veiculo = veiculoDAO.verVeiculo(placa);
		if (veiculo == null) {
			System.err.println("Nenhum veículo encontrado");
			
		} else {
			if (veiculo.getMultas().isEmpty()) {
				System.err.println("Nenhuma multa encontrada");
				
			} else {	
				for (Multa m : veiculo.getMultas()) {
					System.out.println(m);
				}
			}
		}
	}

	private static void listarMultas() {
		System.out.println();
		System.out.println("Listar multas");
		System.out.println();
		
		List<Multa> multas = multaDAO.listarMultas();
		
		if (multas.isEmpty()) {
			System.err.println("Nenhuma multa encontrada");
			
		} else {
			for (Multa m : multas) {
				System.out.println(m);
			}
			
		}
	}
	
	public static int inicio() {
		System.out.println();
		System.out.println("MENU");
		System.out.println();
		System.out.println("1-Condutor");
		System.out.println("2-Veículo");
		System.out.println("3-Multa");
		System.out.println("4-Sair");
		System.out.println();
		System.out.print("Escolha uma opção: ");
		return sc.nextInt();

	}

}
