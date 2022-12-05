package br.com.mesttra.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import br.com.mesttra.entity.Condutor;

public class CondutorUI {

	public static void menu() {
		System.out.println();
		System.out.println("Menu condutor");
		System.out.println();
		System.out.println("1-Cadastrar condutor");
		System.out.println("2-Remover condutor");
		System.out.println("3-Consultar condutor");
		System.out.println("4-Listar condutores");
		System.out.println("5-Voltar para menu principal");
		System.out.println();
		System.out.print("Escolha uma opção: ");
	}
	
	public static Condutor cadastrar(Scanner sc) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Condutor condutor = new Condutor();
		
		System.out.print("Número da CNH: ");
		String nroCnh = sc.next();
		
		System.out.print("Data de emissão: ");
		LocalDate dataEmissao = LocalDate.parse(sc.next(), formatter);
		
		System.out.print("Orgão emissor: ");
		String orgaoEmissor = sc.next();
		
		condutor.setNroCnh(nroCnh);
		condutor.setDataEmissao(dataEmissao);
		condutor.setOrgaoEmissor(orgaoEmissor);
		condutor.setPontuacao(0);
		
		return condutor;
	}
}
