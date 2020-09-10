package br.com.alexandre.git;

import java.util.Scanner;

public class Pessoa {
   private int ID;
   private String Nome;
   private String Endereco;
private Scanner sc;
   
   public int getID() {
	   return ID;
   }
   public void setID(int identidade) {
	   ID = identidade;
   }
   public String getNome() {
	   return Nome;
   }
   public void setNome(String nomeCliente) {
	   Nome = nomeCliente;
   }
   
   public String getEndereco() {
	   return Endereco;
   }
   public void setEndereco(String endereco) {
	   Endereco = endereco;
   }
   
   public void Tipo_Pessoa() {
	   Pessoa_Fisica pessoa_fisica = new Pessoa_Fisica();
	   Pessoa_Juridica pessoa_juridica = new Pessoa_Juridica();
	   
	   System.out.println("Digite o tipo de Pessoa, "
	   		+ " 1 - Pessoa fisica ou 2 - para Pessoa Juridica:");
	   sc = new Scanner(System.in);
	   int opcao;
	   opcao = sc.nextInt();
	   
	   switch (opcao) {
	   
	   case 1: 
		       System.out.println("Digite os três primeiros digitos do seu CPF: ");
		       Scanner cpfPessoa = new Scanner(System.in);
		       int cpf = 0;
		       cpf = cpfPessoa.nextInt();  
		       pessoa_fisica.setCPF(cpf);
		       
	           System.out.println("\nDigite a data de nascimento somente o Ano: ");
	           Scanner anoNasc = new Scanner(System.in);
	           int ano = 0;
	           ano = anoNasc.nextInt();
	           pessoa_fisica.setDtnascimento(ano);
	           
	           System.out.println("\nDigite seu Gênero: ");
	           Scanner generoPessoa = new Scanner(System.in);
	           String genero;
	           genero = generoPessoa.nextLine();
	           pessoa_fisica.setGenero(genero);
	           getID();
	           getNome();
	           getEndereco();
	           
	           break;       
	  
	   case 2: 
		       System.out.println("Digite os três primeiros digitos do seu CNPJ: ");
		       Scanner cnpjPessoa = new Scanner(System.in);
		       int cnpj = 0;
		       cnpj = cnpjPessoa.nextInt();  
		       pessoa_juridica.setCNPJ(cnpj);
	           System.out.println("\nDigite Sua Atividade: ");
	           Scanner atividadeCNPJ = new Scanner(System.in);
	           String atividade;
	           atividade = atividadeCNPJ.nextLine();
	           pessoa_juridica.setAtividade(atividade);
	           break;
	   default:
		   break;
	   }
   }
}
