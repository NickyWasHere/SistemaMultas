package br.com.mesttra.entity;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class Condutor {

	@Id
	@Column (name = "nro_cnh")
	private String nroCnh;
	
	@Column (name = "data_emisao", nullable = false)
	private LocalDate dataEmissao;
	
	@Column (name = "orgao_emissor", nullable = false)
	private String orgaoEmissor;
	
	@Column (nullable = false)
	private int pontuacao;
	
	@OneToOne
	@JoinColumn (name = "veiculo_fk", referencedColumnName = "placa")
	private Veiculo veiculo;

	public String getNroCnh() {
		return nroCnh;
	}

	public void setNroCnh(String nroCnh) {
		this.nroCnh = nroCnh;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
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
		return "\nNúmero da CNH: " + nroCnh +
				"\nData de emissão: " + dataEmissao +
				"\nOrgão emissor: " + orgaoEmissor +
				"\nPontuação: " + pontuacao;
	}
	
}
