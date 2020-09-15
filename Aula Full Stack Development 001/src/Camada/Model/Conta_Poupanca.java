package Camada.Model;

import Camada.Ctrl.Conta;

public class Conta_Poupanca extends Conta {
	private double txCorrecao;
	
	public double getTaxaCorrecao() {
		return txCorrecao;
	}
	public void setTaxaCorrecao(double txCorrecaoConta) {
		txCorrecao = txCorrecaoConta;
	}
	
	public void atualizaSaldoRendimento() {
		double taxaDeCorrecao = 50;
		setTaxaCorrecao(taxaDeCorrecao);
	}
	
	public void abrirConta() {
		 this.setSaldo(txCorrecao);
	}
}
