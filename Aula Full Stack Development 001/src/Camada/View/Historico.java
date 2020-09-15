package Camada.View;

import Camada.Ctrl.Conta;

public class Historico {
 private Conta conta;
 private int tipoMovimentacao;
 private double valorDeposito;
 private double valorSaque;
 private double valorTranferido;
 
   public Conta getConta() {
	   return conta;
   }
   
   public void setConta(Conta conta) {
	   this.conta = conta;
   }
   
   public int getMovimentacao() {
	   return tipoMovimentacao;
   }
   public void setTipoMovimentacao(int movimentacao) {
	   this.tipoMovimentacao = movimentacao;
   }
   
   public double getValordepositado() {
	   return valorDeposito;
   }
   public void setValorDepositado(double valorDep) {
	   this.valorDeposito = valorDep;
   }

   public double getValorSaque() {
	   return valorSaque;
   }
   public void setValorSaque(double valorSaque) {
	   this.valorSaque = valorSaque;
   }
   
   public double getValorTransferido() {
	   return valorTranferido; 
   }
   
   public void setValortransferido(double valorTrans) {
	   valorTranferido = valorTrans;
   }
   
   
   public void mostrarContaMovimentacao() {
	   if(getMovimentacao() == 2) {
		   System.out.print("\nMovimentação de Alteração na Conta");
		   System.out.print("\nCategoria: " + getConta().getCategoria().getCategoria());
		   System.out.print("\nNome:" + getConta().getCliente().getNome());
		   System.out.print("\nEndereço: " + getConta().getCliente().getEndereco());
		   System.out.print("\nIdentidade: " + getConta().getCliente().getID());
		   
		   if(getConta().getCliente().getPessoa_Fisica() != null) {
				System.out.println("Três primeiros digitos do CPF: " + getConta().getCliente().getPessoa_Fisica().getCPF());
				System.out.println("Ano Nascimento: " + getConta().getCliente().getPessoa_Fisica().getDTnascimento());
				System.out.println("Genero: " + getConta().getCliente().getPessoa_Fisica().getGenero());
			}
			if(getConta().getCliente().getPessoa_Juridica() != null) {
				System.out.println("CNPJ: " + getConta().getCliente().getPessoa_Juridica().getCNPJ());
				System.out.println("Atividade: " + getConta().getCliente().getPessoa_Juridica().getAtividade()+"\n\n");
	        }   
	   }
	   
	   if(getMovimentacao() == 3) {
    	   System.out.print("\nMovimentação de Deposito");
    	   System.out.print("\nNome:"+ getConta().getCliente().getNome());
    	   System.out.print("\nValor Depositado:" + getValordepositado());
       }
	   
       if(getMovimentacao() == 4) {
    	   System.out.print("\nMovimentação de Saque");
    	   System.out.print("\nNome:"+ getConta().getCliente().getNome());
    	   System.out.print("\nValor de Saque:" + getValorSaque());
	   }
       
       if(getMovimentacao() == 5) {
    	   System.out.print("\nMovimentação de transferência");
    	   System.out.print("\nNome:"+ getConta().getCliente().getNome());
    	   System.out.print("Valor Transferido:" + getValorTransferido());
	   }
	   
   }
   
      
}
