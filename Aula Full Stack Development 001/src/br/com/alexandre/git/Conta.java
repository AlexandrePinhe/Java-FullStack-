package br.com.alexandre.git;

import java.util.Scanner;

public class Conta {
	private Pessoa pessoa;
	private int nrConta;
	private double saldo;
	private Scanner tipoConta;
	
	public Pessoa getCliente() {
		return pessoa;
	}
	public void setCliente(Pessoa cliente) {
	  pessoa = cliente;
	}	
	public int getNrconta() {
		return nrConta;
	}
	public void setNrConta(int conta) {
	  nrConta= conta;	
	}
	public double getSaldo(){
		return saldo;
		}
	public void setSaldo(double saldoConta) {
		saldo = saldoConta;
	}
	
	public void abrirConta() {
		Conta_Especial conta_especial = new Conta_Especial();
		Conta_Poupanca conta_poupanca = new Conta_Poupanca();
		
		System.out.println("Tipo de conta: 1 = Especial | 2 = Poupança: ");
		tipoConta = new Scanner(System.in);
		int tipo;
		tipo = tipoConta.nextInt();
		 switch (tipo) {
		        case 1:	        	  
		        	  conta_especial.temSaldoLimite();
		        	  setSaldo(conta_especial.getLimite());
		              conta_especial.abrirConta();
		        	  break;
		        case 2:
		        	  conta_poupanca.atualizaSaldoRendimento();
		        	  setSaldo(saldo + conta_poupanca.getTaxaCorrecao());
		        	  conta_poupanca.abrirConta();
		        	  break;
		        default: 
		        	break;
		 }
	}
	
	public void sacar(double valor) {
		if(valor < getSaldo() && valor > 0){
			debitar(valor);
			}else{
			if(valor < 0){
			System.out.println("\nValor invalido\n\n");
			}else{
			System.out.println("Saldo Insuficiente\n\n");
			}
			}
	}
	
	private void debitar(double valor) {
		saldo -= valor;
	}
	
	public String temSaldo() {
		
		if(saldo > 0) {
			return ("Seu saldo em conta é de = "+saldo);
		}
		else
			return("Sem saldo na conta");
	}
	
	public void depositar(double valor){
		if(valor >= 0){
		saldo+=valor;
		}else{
		System.out.print("\nValor invalido\n\n");
	    }
    }
	
	public void transferir(Conta destino, double valor) {
		this.saldo = this.saldo - valor;
        destino.saldo = destino.saldo + valor;
	}

}
