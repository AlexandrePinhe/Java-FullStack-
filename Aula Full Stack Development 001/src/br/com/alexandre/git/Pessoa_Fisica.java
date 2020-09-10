package br.com.alexandre.git;

public class Pessoa_Fisica extends Pessoa{
   private int CPF;
   private int dtNascimento;
   private String Genero;
   
   public int getCPF() {
	   return CPF;
   }  
   public void setCPF(int cpfPessoaFisica) {
	   CPF = cpfPessoaFisica;
   }
   public int getDTnascimento() {
	   return dtNascimento;
   }
   public void setDtnascimento(int Dtnascimento) {
	   this.dtNascimento = Dtnascimento;
   }
   public String getGenero() {
	   return Genero;
   }
   public void setGenero(String genero) {
	   Genero = genero;
   }
   
   public int getIdade() {
	   dtNascimento -= 2020;
	   int idadePessoa = Math.abs(dtNascimento);
	   return idadePessoa;
   }
}
