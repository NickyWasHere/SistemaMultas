package br.com.mesttra.ui;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.mesttra.entity.*;

public class VeiculoUI {

	public static void menu() {
		System.out.println();
		System.out.println("Menu veículo");
		System.out.println();
		System.out.println("1-Cadastrar veículo");
		System.out.println("2-Remover veículo");
		System.out.println("3-Consultar veículo");
		System.out.println("4-Listar veículos");
		System.out.println("5-Vender veículo");
		System.out.println("6-Voltar para menu principal");
		System.out.println();
		System.out.print("Escolha uma opção: ");
	}
	
	public static Veiculo cadastrar(Scanner sc) {
		Veiculo veiculo = new Veiculo();
		
		System.out.print("Placa: ");
		String placa = sc.next();
		
		System.out.print("Modelo: ");
		String modelo = sc.next();
		
		System.out.print("Marca: ");
		String marca = sc.next();
		
		System.out.print("Ano: ");
		int ano = sc.nextInt();
		
		veiculo.setPlaca(placa);
		veiculo.setModelo(modelo);
		veiculo.setMarca(marca);
		veiculo.setAno(ano);
		veiculo.setMultas(new ArrayList<Multa>());
		
		return veiculo;
	}
}
