package br.com.alexandre.git;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static Scanner sc;

	public static void main(String[] args) {

		ArrayList<Conta> contas = new ArrayList<Conta>();
		
		int TotalDeContas = 50;
		
		for(int i = 1; i < TotalDeContas; i++){
			
			System.out.print("\n[1] - Criar Conta");
			System.out.print("\n[2] - Depositar");
			System.out.print("\n[3] - Sacar");
			System.out.print("\n[4] - Transferir");
			System.out.print("\n[5] - Imprimir Todos usuários");
			System.out.print("\n[6] - Sair");

			sc = new Scanner(System.in);
			int opcao;

			System.out.print("\n\nQual a opção escolhida? ");
            opcao = sc.nextInt();
                
            switch(opcao){
                          case 1: 
                        	      Conta conta = new Conta();
                        	      Pessoa pessoa = new Pessoa();
                        	      
                                  System.out.println("Digite Número da Conta: ");
                  		           Scanner numeroConta = new Scanner(System.in);
                  		           int numConta = 0;
                  		            numConta = numeroConta.nextInt();
                  		           conta.setNrConta(numConta);
                  		          
                  		
                  		           System.out.println("Digite seu nome: ");
                  		           Scanner nomePessoa = new Scanner(System.in);
                  		           String NomePEssoa;
                  		           NomePEssoa = nomePessoa.nextLine();
                  		           pessoa.setNome(NomePEssoa);
                  		
                  		          System.out.println("Digite seu Endereço: ");
                  		          Scanner enderecoPessoa = new Scanner(System.in);
                  		          String EnderecoPessoa;
                  		          EnderecoPessoa = enderecoPessoa.nextLine();
                  		          pessoa.setEndereco(EnderecoPessoa);
                  		          
                  		          System.out.println("Digite sua Identidade:");
                  		          Scanner identidade = new Scanner(System.in);
                  		          int identidadePessoa = 0;
                  		          identidadePessoa = identidade.nextInt();
                  		          pessoa.setID(identidadePessoa);
                  		          
                  		          pessoa.Tipo_Pessoa();
                  		          conta.setCliente(pessoa);
                                  conta.abrirConta();
                                  
                                  contas.add(conta);
                                  i++;;
            	              break;
            	             
                          case 2: 
                        	  if(contas.isEmpty() == true){
   					                     System.out.print("\nERRO! Nenhuma conta cadastrada\n");
   					              break;
   				                 }
                        	      
                                  System.out.print("\nInforme o numero da conta: ");
                                  int nmrConta = sc.nextInt();  
                                  nmrConta--;
                                  
                                	  double dep = 0;
              				          System.out.printf("Insira o valor do deposito:R$ ");
              				          dep = sc.nextDouble();
              				          contas.get(nmrConta).depositar(dep);                              	  
                        	  break;
                        	  
                          case 3:  if(contas.isEmpty() == true){
			                           System.out.print("\nERRO! Nenhuma conta cadastrada\n");
			                       break;
		                           }
                                  System.out.print("\nInforme o numero da conta: ");
                                  int nmrContaSacar = sc.nextInt();  
                                  nmrContaSacar--;
                                 
                                  double sac = 0;
                                  System.out.printf("Insira que deseja Sacar:R$ ");
                                  sac = sc.nextDouble();
                                  contas.get(nmrContaSacar).sacar(sac);
                        	  break;
                        	  
                          case 4: if(contas.isEmpty() == true){
			                     System.out.print("\nERRO! Nenhuma conta cadastrada\n");
			                      break;
		                          }
                                  System.out.print("\nInforme o numero da sua conta: ");
                                  int nmrContaTranferir = sc.nextInt();  
                                  nmrContaTranferir--;
                                  
                                  double trans = 0;
                                  System.out.printf("Insira o valor que deseja transferir:R$ ");
                                  trans = sc.nextDouble();
                                  
                                  System.out.println("informe o numero da conta de destino: ");
                                  int destino = sc.nextInt();
                                  destino--;
                                  
                                  contas.get(nmrContaTranferir).transferir(contas.get(destino), trans);
                        	  break;
                        	  
                          case 5:    if(contas.isEmpty() == true){
			                            System.out.print("\nERRO! Nenhuma conta cadastrada\n");
			                         break;
		                             }
                            double saldoTotaldasContas = 0;
                  			for(int x = 0; x < contas.size(); x++) {
                  				System.out.println("\nNome: " + contas.get(x).getCliente().getNome());
                  				System.out.println("Saldo: " +contas.get(x).getSaldo());
                  				
                  				saldoTotaldasContas += contas.get(x).getSaldo();
                  			}
                  			
                  			System.out.println("\nSaldo Total das Contas: " + saldoTotaldasContas);
                        	  break;
                          case 6: System.exit(0);
                              break;
                  
            }
		}
 
	}

}
