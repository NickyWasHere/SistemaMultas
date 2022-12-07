package br.com.mesttra.entity;

import javax.persistence.*;

@Entity
public class Multa {

	@Id
	@Column (name = "codigo_multa")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int codigoMulta;
	
	@Column (nullable = false)
	private double valor;
	
	@Column (nullable = false)
	private int pontuacao;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "veiculo_fk", referencedColumnName = "placa")
	private Veiculo veiculo;

	public int getCodigoMulta() {
		return codigoMulta;
	}

	public void setCodigoMulta(int codigoMulta) {
		this.codigoMulta = codigoMulta;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	@Override
	public String toString() {
		return "Código:" + codigoMulta +
				"\nValor: " + valor +
				"\nPontuação: " + pontuacao +
				"\nPlaca associada: " + veiculo.getPlaca();
	}
	
}
