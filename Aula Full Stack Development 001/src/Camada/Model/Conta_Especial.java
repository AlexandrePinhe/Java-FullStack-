package Camada.Model;

import Camada.Ctrl.Conta;

public class Conta_Especial extends Conta{
   private double limite;
   
   public double getLimite() {
	   return limite;
   }
   public void setLimite(double limiteConta) {
	   limite = limiteConta;
   }
   
   public double temSaldoLimite() {
		limite = 900;
		return limite;
	}
   public void abrirConta() {
	   this.setSaldo(limite);
   }
}
