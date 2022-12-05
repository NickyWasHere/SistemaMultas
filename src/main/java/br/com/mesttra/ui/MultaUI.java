package br.com.mesttra.ui;

import java.util.Scanner;

import br.com.mesttra.entity.Multa;

public class MultaUI {
	
	public static void menu() {
		System.out.println();
		System.out.println("Menu multa");
		System.out.println();
		System.out.println("1-Cadastrar multa");
		System.out.println("2-Remover multa");
		System.out.println("3-Consultar multa");
		System.out.println("4-Listar multas de um veículo");
		System.out.println("5-Listar todas as multas");
		System.out.println("6-Voltar para menu principal");
		System.out.println();
		System.out.print("Escolha uma opção: ");
	}
	
	public static Multa cadastrar(Scanner sc) {
		Multa multa = new Multa();
		
		System.out.print("Valor: ");
		double valor = sc.nextDouble();
		
		System.out.print("Pontuação: ");
		int pontuacao = sc.nextInt();
		
		multa.setValor(valor);
		multa.setPontuacao(pontuacao);
		
		return multa;
	}
}
