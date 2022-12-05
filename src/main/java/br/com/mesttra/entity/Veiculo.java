package br.com.mesttra.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class Veiculo {
	
	@Id
	private String placa;
	
	@Column (nullable = false)
	private int ano;
	
	@Column (nullable = false)
	private String modelo;
	
	@Column (nullable = false)
	private String marca;
	
	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "nro_cnh_fk", referencedColumnName = "nro_cnh")
	private Condutor condutor;
	
	@OneToMany (mappedBy = "veiculo", fetch = FetchType.LAZY)
	private List<Multa> multas;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Condutor getCondutor() {
		return condutor;
	}

	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}

	public List<Multa> getMultas() {
		return multas;
	}

	public void setMultas(List<Multa> multas) {
		this.multas = multas;
	}

	@Override
	public String toString() {
		return "\nPlaca do veículo: " + placa +
				"\nCNH associada: " + condutor.getNroCnh() +
				"\nModelo: " + modelo + " - " + ano +
				"\nMarca: " + marca;
	}
}
