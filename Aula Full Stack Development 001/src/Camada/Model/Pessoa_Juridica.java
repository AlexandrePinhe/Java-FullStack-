package Camada.Model;

import Camada.Ctrl.Pessoa;

public class Pessoa_Juridica extends Pessoa{
   private int CNPJ;
   private String Atividade;
   
   public int getCNPJ() {
	   return CNPJ;
   }  
   public void setCNPJ(int cnpjPessoaJuridica) {
	   CNPJ = cnpjPessoaJuridica;
   }
   
   public String getAtividade() {
	   return Atividade;
   }
   public void setAtividade(String atividade) {
	   Atividade = atividade;
   }
}
