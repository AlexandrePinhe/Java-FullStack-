package Camada.View;

import java.util.ArrayList;
import java.util.Scanner;

import Camada.Ctrl.Categoria;
import Camada.Ctrl.Conta;
import Camada.Ctrl.Pessoa;

public class Main {

	private static Scanner sc;
	private static Scanner nomePessoa;
	private static Scanner enderecoPessoa;
	private static Scanner identidade;

	public static void main(String[] args) {

		ArrayList<Conta> contas = new ArrayList<Conta>();
		ArrayList<Historico> historicos = new ArrayList<Historico>();
   		
   		
		int TotalDeContas = 5000;
		
		for(int i = 1; i < TotalDeContas; i++){
			
			System.out.print("\n 1 - Abrir Conta");
			System.out.print("\n 2 - Selecionar Conta");
            System.out.print("\n 3 - Cadastrar Cliente");
			System.out.print("\n 4 - Relatórios");
			System.out.print("\n 5 - Sair");

			sc = new Scanner(System.in);
			int opcao;

			System.out.print("\n\nQual a opção escolhida? ");
            opcao = sc.nextInt();
                
            switch(opcao){
                          case 1: 
                        	      Conta conta = new Conta(); 
                        	      
                                  System.out.println("Digite Número da Conta: ");
                  		           Scanner numeroConta = new Scanner(System.in);
                  		           int numConta = 0;
                  		            numConta = numeroConta.nextInt();
                  		           conta.setNrConta(numConta);
                  		           
                  		          System.out.println("Digite categoria da conta \n 1 - Simples\n 2 - Executiva\n "
                  		          		+ "3 - Premiun \n 4 - Personalite ");
                  		          Scanner categoriaConta = new Scanner(System.in);
                  		         int opcaoCategoria;
                  		       opcaoCategoria = categoriaConta.nextInt();
                  		           if(opcaoCategoria == 1) { conta.set_Categoria(Categoria.Simples); }
                  		           if(opcaoCategoria == 2) { conta.set_Categoria(Categoria.Executiva); }
                  		           if(opcaoCategoria == 3) { conta.set_Categoria(Categoria.Premiun); }
                  		           if(opcaoCategoria == 4) { conta.set_Categoria(Categoria.Personalite); }
                  		          
                                  conta.abrirConta();
                                  
                                  contas.add(conta);
                                  i++;;
            	              break;
            	             
                          case 2: 
                        	  System.out.print("\n 2 - Alterar Conta");
                        	  System.out.print("\n 3 - Deposito");
                  			  System.out.print("\n 4 - Saque");
                  			  System.out.print("\n 5 - Transferência");
                  			  System.out.print("\n 6 - Saldo");
                  			  System.out.print("\n 7 - Extrato");
                  			  System.out.print("\n 8 - Histórico de Movimentação");
                  			  System.out.print("\n 9 - Retornar");
                  			  
                  			  sc = new Scanner(System.in);
                			  int opcaoSelecionarConta;
                             
                			  System.out.print("\n\nQual a opção escolhida? ");
                			  opcaoSelecionarConta = sc.nextInt();
                			  switch(opcaoSelecionarConta) {
                			        case 2: 
                			        	try {
                			        		if(contas.isEmpty() == true){
                   			        		 throw new Classe_EXCEPTION("ERRO! Nenhuma conta cadastrada");
                   			        	 }
 
                			        		
                			        		System.out.print("\nInforme o numero da conta que deseja Alterar: ");
                                            int nmrContaParaAlterar = sc.nextInt();  
                                            nmrContaParaAlterar--;
                                            
                                            contas.get(nmrContaParaAlterar).Alterar_Conta();
                                            
                                            Historico historico = new Historico();
                                            historico.setConta(contas.get(nmrContaParaAlterar));
                                            historico.setTipoMovimentacao(2);
                                            historicos.add(historico);
                                            
                			            }catch(Classe_EXCEPTION msg) {
                			            	System.err.println(msg.getMessage());
                			            }
                			        	
                			        	break;
                			        	
                			        case 3:
                			        try {
                			        	 if(contas.isEmpty() == true){
                			        		 throw new Classe_EXCEPTION("ERRO! Nenhuma conta cadastrada");
                			        	 }
                			        	 
                			        	 
                			        	 System.out.print("\nInforme o numero da conta: ");
                                         int nmrConta = sc.nextInt();  
                                         nmrConta--;
                                         
                                       	  double dep = 0;
                     				          System.out.printf("Insira o valor do deposito:R$ ");
                     				          dep = sc.nextDouble();
                     				          contas.get(nmrConta).depositar(dep); 
                     				      
                     				      Historico historico = new Historico();
                     				      historico.setTipoMovimentacao(3);
                     				      historico.setValorDepositado(dep);   
                     				      historico.setConta(contas.get(nmrConta));
                     				      
                                          historicos.add(historico);
                                             
                			        }catch(Classe_EXCEPTION msg) {
                			        	System.err.println(msg.getMessage());
                			        }
                			        	break;
                			        	
                			        case 4:
                			        	try {
                			        		 if(contas.isEmpty() == true){
                    			        		 throw new Classe_EXCEPTION("ERRO! Nenhuma conta cadastrada");
                    			        	 }
                			        		 
                			        		 
                			        		 System.out.print("\nInforme o numero da conta: ");
                                             int nmrContaSacar = sc.nextInt();  
                                             nmrContaSacar--;
                                            
                                             double sac = 0;
                                             System.out.printf("Insira que deseja Sacar:R$ ");
                                             sac = sc.nextDouble();
                                             
                                             contas.get(nmrContaSacar).sacar(sac);
                                             
                                             Historico historico = new Historico();
                                             historico.setTipoMovimentacao(4);
                                             historico.setValorSaque(sac);
                                             historico.setConta(contas.get(nmrContaSacar));
                                             
                                             historicos.add(historico);
                    			        	
                    			        }catch(Classe_EXCEPTION msg) {
                    			        	System.err.println(msg.getMessage());
                    			        }
                			        	
                			        	break;
                			        	
                			        case 5:
                			        	try {
                			        		 if(contas.isEmpty() == true){
                    			        		 throw new Classe_EXCEPTION("ERRO! Nenhuma conta cadastrada");
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
                                             
                                             Historico historico = new Historico();
                                             historico.setValortransferido(trans);
                                             historico.setConta(contas.get(nmrContaTranferir));
                                             historico.setTipoMovimentacao(5);
                                             historicos.add(historico);
                    			        	
                    			        }catch(Classe_EXCEPTION msg) {
                    			        	System.err.println(msg.getMessage());
                    			        }
                			        	break;
                			        	
                			        case 6: 
                			        	try {
                			        		 if(contas.isEmpty() == true){
                    			        		 throw new Classe_EXCEPTION("ERRO! Nenhuma conta cadastrada");
                    			        	 }
                			        		 
                			        		 System.out.print("\nInforme o numero da sua conta: ");
                                             int nmrConta = sc.nextInt();  
                                             nmrConta--;
                                             
                                             contas.get(nmrConta).temSaldo();
                                              
                    			        }catch(Classe_EXCEPTION msg) {
                    			        	System.err.println(msg.getMessage());
                    			        }
                			        	break;
                			        	
                			        case 7:
                			        	try {
                			        		 if(contas.isEmpty() == true){
                    			        		 throw new Classe_EXCEPTION("ERRO! Nenhuma conta cadastrada");
                    			        	 }
                			        		 
                			        		 
                			        		 
                			        		 System.out.print("\nInforme o numero da conta: ");
                                             int nmrContaExtrato = sc.nextInt();  
                                             nmrContaExtrato--;
                                             
                                             if(contas.get(nmrContaExtrato).getCliente() == null) {
                                            	 throw new Classe_EXCEPTION("ERRO! Nenhum Cliente cadastrado");
                                             }
                                             
                                             contas.get(nmrContaExtrato).Extrato();    
                    			        	
                    			        }catch(Classe_EXCEPTION msg) {
                    			        	System.err.println(msg.getMessage());
                    			        }
                			        	 break;
                			        case 8: 
                			        	try {
                			        		if(contas.isEmpty() == true){
                   			        		 throw new Classe_EXCEPTION("ERRO! Nenhuma conta cadastrada");
                   			        	    }
                                                              
                                            for(int x = 0; x < historicos.size(); x++) {
                                            	historicos.get(x).mostrarContaMovimentacao();                              
                                  			}
                			        	}catch(Classe_EXCEPTION msg) {
                			        		System.err.println(msg.getMessage());
                			        	}
                			        	
                			        	 break;	
                			        	 
                			        case 9: return;         			        
                			  }
                        	  
                        	      
                                                             	  
                        	  break;
                        	  
                          case 3: 
                        	  try {
                        		  if(contas.isEmpty() == true){
          			        		 throw new Classe_EXCEPTION("ERRO! Nenhuma conta cadastrada");
          			        	 }
                        		  System.out.print("\nInforme o numero da conta que deseja cadastrar o Cliente: ");
                                  int nmrContaCliente = sc.nextInt();  
                                  nmrContaCliente--;
                                  
                                  Pessoa pessoa = new Pessoa();
                                  
                                  System.out.println("Digite seu nome: ");
                 		           nomePessoa = new Scanner(System.in);
                 		           String NomePEssoa;
                 		           NomePEssoa = nomePessoa.nextLine();
                 		           pessoa.setNome(NomePEssoa);
                 		
                 		          System.out.println("Digite seu Endereço: ");
                 		          enderecoPessoa = new Scanner(System.in);
                 		          String EnderecoPessoa;
                 		          EnderecoPessoa = enderecoPessoa.nextLine();
                 		          pessoa.setEndereco(EnderecoPessoa);
                 		          
                 		          System.out.println("Digite sua Identidade:");
                 		          identidade = new Scanner(System.in);
                 		          int identidadePessoa = 0;
                 		          identidadePessoa = identidade.nextInt();
                 		          pessoa.setID(identidadePessoa);
                 		          
                 		          pessoa.Tipo_Pessoa();
                 		          
                 		          contas.get(nmrContaCliente).setCliente(pessoa);
                                  
                        		  
                        	  }catch(Classe_EXCEPTION msg){
                        		  System.err.println(msg.getMessage());
                        	  }
                                  
                        	  
                        	  break;
                        	  
                          case 4: 
                        	  try {
                        		  if(contas.isEmpty() == true){
         			        		 throw new Classe_EXCEPTION("ERRO! Nenhuma conta cadastrada");
         			        	 }
                        	     
                        		  System.out.print("\n 1 - Saldo das Contas");
                            	  System.out.print("\n 2 - Total das Contas");
                            	  sc = new Scanner(System.in);
                            	  int opcaorelatorio;
                            	  
                            	  System.out.println("\nOpção escolhida: ");
                            	  opcaorelatorio = sc.nextInt();
                            	  
                            	  if(opcaorelatorio == 1) {
                            		  for(int x = 0; x < contas.size(); x++) {
                          				System.out.println("\nNome: " + contas.get(x).getCliente().getNome());
                          				System.out.println("Saldo: " +contas.get(x).getSaldo());                                      
                          			}
                            	  }
                            	  if(opcaorelatorio == 2) {
                            		   double saldoTotaldasContas = 0;
                            		   for(int x = 0; x < contas.size(); x++) {
                            			   saldoTotaldasContas += contas.get(x).getSaldo();
                            			   }
                            			System.out.println("\nSaldo Total das Contas: " + saldoTotaldasContas);
                            		  }	   
                            	  
                              }catch(Classe_EXCEPTION msg) {
                            		System.err.println(msg.getMessage());
                              }     
                        	  break;
                          case 5:    
                        	  System.exit(0);
                        	  break;               
            }
		}
 
	}

}
