package Entidade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


public class BancoProvisorio {
    List<Carro> carros;
    List<Modelo> modelos;
    List<Fabricante> fabricantes;
    
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultset = null;
    
    public void Conectar() {
    	String servidor = "jdbc:mysql://localhost:3306/carro?autoReconnect=true&useSSL=false";
    	String usuario = "Alex";
    	String senha = "root";
    	String driver = "com.mysql.jdbc.Driver";
    	
    	try {
    		Class.forName(driver);
    		this.connection = DriverManager.getConnection(servidor, usuario, senha);
    		this.statement = this.connection.createStatement();
    		System.out.print("Conexão inciada com Sucesso !");
    	} catch(Exception e) {
    		System.out.println("Erro: "+ e.getMessage());
    	}
    }
    
    public boolean estaConectado() {
    	if(this.connection != null) {
    		return true;
    	}else
    		return false;
    }
    
    public void desconectar() {
    	try {
    		this.connection.close();
    		System.out.print("Conexão encerrada com Sucesso!");
    	}catch(Exception e) {
    		System.out.print("ERRO: " + e.getMessage());
    	}
    }
    
    public void inserirFabricante() {
        	
    }
    
    //CRUD do CARRO
    public void listarCarros() {
    	
    	try {
    		String query = "SELECT * FROM carro.tb_carro ";
    		this.resultset = this.statement.executeQuery(query);
    		while(this.resultset.next()) {
    			System.out.println("\nCarro ID: " + this.resultset.getString("carroId") + "|" + "Placa: " + this.resultset.getString("placa") + "|" + 
    		"Ano: " +  this.resultset.getString("ano") + "|" + "Tipo de carro: " +  this.resultset.getString("tipoCarro") + "|" + "Modelo ID : " +  this.resultset.getString("modeloId"));
    		}
    		
    	}catch(Exception e) {
    		System.out.print("Erro : " + e.getMessage());
    }   	
  }
    
    public void inserirCarro(int id, String placa, int ano, int tipoCarro, int modeloId) {
    	
	   try {
		   String query = "INSERT INTO carro.tb_carro  (carroId, placa, ano ,tipoCarro, modeloId) VALUES ("+id +",'"+placa+"',"+ano+", "+tipoCarro+", "+modeloId+");";
		   this.statement.executeUpdate(query);	   
	   }catch(Exception e){
		   System.out.print("Erro: " + e.getMessage());
	   }
    }
    
    public void deletarCarro(int ID) {
    	try {
    		String query = "DELETE FROM carro.tb_carro WHERE carroId = "+ID+";";
    		this.statement.executeUpdate(query);
    	}catch(Exception e) {
    		System.out.print("Erro: " + e.getMessage());
    	}
    }
    
    public void editarCarro(int id, String placa, int ano, int tipoCarro, int modeloId) {
    	try {
    		String query ="UPDATE carro.tb_carro  SET placa = '"+placa+"', ano = "+ano+", tipoCarro = "+tipoCarro+", modeloId = "+modeloId+" WHERE carroId = "+id+"; ";
    		this.statement.executeUpdate(query);
    	}catch(Exception e) {
    		System.out.print("Erro: " + e.getMessage());
    	}
    }
//CRUD CARRO
//-----------------------------------------------------------------------------------------------------------------------------------------------
//CRUD MODELO
    
    public void inserirModelo(int idModelo, String nomeModelo, int idFabricante) {
    	try {
 		   String query = "INSERT INTO carro.tb_modelo (modeloId, modeloNome, fabricanteId) VALUES ("+idModelo +",'"+nomeModelo+"',"+idFabricante+");";
 		   this.statement.executeUpdate(query);	   
 	   }catch(Exception e){
 		   System.out.print("Erro: " + e.getMessage());
 	   }
    }
    
    public void deletarModelo(int idModelo) {
    	
    	try {
    		String query = "DELETE FROM carro.tb_modelo WHERE modeloId = "+idModelo+";";
    		this.statement.executeUpdate(query);
    	}catch(Exception e) {
    		System.out.print("Erro: " + e.getMessage());
    	}
    }
    
    public void editarModelo(int idModelo, String nomeModelo, int idFabricante) {
    	
    	try {
    		String query ="UPDATE carro.tb_modelo  SET modeloNome = '"+nomeModelo+"', fabricanteId = "+idFabricante+" WHERE modeloId = "+idModelo+";";
        	this.statement.executeUpdate(query);
    	}catch(Exception e) {
    		System.out.print("Erro: " + e.getMessage());
    	}
    }
    
    public void buscarModelo() {
    	
    	try {
    		String queryModelo = "SELECT * FROM carro.tb_modelo ";
        	this.resultset = this.statement.executeQuery(queryModelo);
        	while(this.resultset.next()) {
    			System.out.println("\n ID Modelo:" + this.resultset.getString("modeloId") + "|" 
        	    + "Nome Modelo: " +this.resultset.getString("modeloNome") + "|" + "Id Fabricante: " + this.resultset.getString("fabricanteId"));
    		}
    	}catch(Exception e) {
    		System.out.print("Erro: " + e.getMessage());
    	}
     }
}
