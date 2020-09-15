package Camada.View;

public class Classe_EXCEPTION extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
    public Classe_EXCEPTION(String msg){
      super(msg);
      this.msg = msg;
    }
    public String getMessage(){
      return msg;
    }  
  }

