package aplicacao;

public class Cliente {

	private String nome, whatsapp, cpf;
	private double totalGasto;
	private boolean apto;

	public Cliente(String nome, String whatsapp, String cpf, double totalGasto, boolean apto) {
		this.nome = nome;
		this.whatsapp = whatsapp;
		this.cpf = cpf;
		this.totalGasto = totalGasto;
		this.apto = apto;
	}

	@Override
	public String toString() {
		return "Cliente{" +
				"nome='" + nome + '\'' +
				", whatsapp='" + whatsapp + '\'' +
				", cpf='" + cpf + '\'' +
				", totalGasto=" + totalGasto +
				", apto=" + apto +
				'}';
	}

	// Getters e Setters (Mantenha os seus)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public double getTotalGasto() {
		return totalGasto;
	}

	public void setTotalGasto(double totalGasto) {
		this.totalGasto = totalGasto;
	}

	public boolean isApto() {
		return apto;
	}

	public void setApto(boolean apto) {
		this.apto = apto;
	}

}