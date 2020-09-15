package Camada.Ctrl;

import java.util.Scanner;

import Camada.Model.Conta_Especial;
import Camada.Model.Conta_Poupanca;
import Camada.Ctrl.Categoria;

public class Conta implements Conta_Interface {
	private Pessoa pessoa;
	private int nrConta;
	private double saldo;
	private Scanner tipoConta;
	private Categoria Categoria;
	private Scanner alterarConta;
	
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
	public Categoria getCategoria() {
		return Categoria;
	}
	public void set_Categoria(Categoria categoria) {
		this.Categoria = categoria;
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
			return ("Seu saldo em conta é de = "+getSaldo());
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
	
	@Override
	public void Alterar_Conta() {
		System.out.print("Deseja Alterar:\n 1 - Categoria \n 2 - Informações do Cliente: ");
		alterarConta = new Scanner(System.in);
		int opcaoAlteraConta;
		opcaoAlteraConta = alterarConta.nextInt();
		
		switch(opcaoAlteraConta) {
		     case 1:
		    	 System.out.println("Digite categoria da conta \n 1 - Simples\n 2 - Executiva\n "
   		          		+ "3 - Premiun \n 4 - Personalite ");
   		          Scanner categoriaConta = new Scanner(System.in);
   		         int opcaoCategoria;
   		       opcaoCategoria = categoriaConta.nextInt();
   		       if(opcaoCategoria == 1) { set_Categoria(Camada.Ctrl.Categoria.Simples); }
   		       if(opcaoCategoria == 2) { set_Categoria(Camada.Ctrl.Categoria.Executiva); }
   		       if(opcaoCategoria == 3) { set_Categoria(Camada.Ctrl.Categoria.Premiun); }
   		       if(opcaoCategoria == 4) { set_Categoria(Camada.Ctrl.Categoria.Personalite); }
			      break;
		     case 2:
		    	 this.getCliente().getNome();
		    	   System.out.println("Digite seu nome: ");
		           Scanner nomePessoa = new Scanner(System.in);
		           String NomePEssoa;
		           NomePEssoa = nomePessoa.nextLine();
		           this.getCliente().setNome(NomePEssoa);
		           
		         this.getCliente().getEndereco();
		          System.out.println("Digite seu Endereço: ");
		          Scanner enderecoPessoa = new Scanner(System.in);
		          String EnderecoPessoa;
		          EnderecoPessoa = enderecoPessoa.nextLine();
		          this.getCliente().setEndereco(EnderecoPessoa);
		          
		         this.getCliente().getID(); 
		          System.out.println("Digite sua Identidade:");
		          Scanner identidade = new Scanner(System.in);
		          int identidadePessoa = 0;
		          identidadePessoa = identidade.nextInt();
		          this.getCliente().setID(identidadePessoa);
		          
		          getCliente().Tipo_Pessoa();
		    	  break;
		    	  
		}
	}
	
	@Override
	public void Extrato() {
		System.out.println("\nConta: " + this.getNrconta());
		System.out.println("Categoria: " + this.Categoria.getCategoria());
		System.out.println("Nome: "+ this.getCliente().getNome());
		System.out.println("Endereço: " + this.getCliente().getEndereco());
		System.out.println("Identidade: " + this.getCliente().getID());
		
		if(getCliente().getPessoa_Fisica() != null) {
			System.out.println("Três primeiros digitos do CPF: " + getCliente().getPessoa_Fisica().getCPF());
			System.out.println("Ano Nascimento: " + getCliente().getPessoa_Fisica().getDTnascimento());
			System.out.println("Genero: " + getCliente().getPessoa_Fisica().getGenero());
		}
		if(getCliente().getPessoa_Juridica() != null) {
			System.out.println("CNPJ: " + getCliente().getPessoa_Juridica().getCNPJ());
			System.out.println("Atividade: " + getCliente().getPessoa_Juridica().getAtividade());
		}
		System.out.println("Saldo: "+ this.getSaldo());
	}
}
