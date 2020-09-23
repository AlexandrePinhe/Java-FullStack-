package View;

import Entidade.BancoProvisorio;

public class Main {
	public static void main(String[] args) {
		
		BancoProvisorio bancoprovisorio = new BancoProvisorio();
		
		bancoprovisorio.Conectar();
		
		if(bancoprovisorio.estaConectado()) {
			//TESTES DOS CRUD's
			
			bancoprovisorio.listarCarros();
			//bancoprovisorio.inserirCarro(4, "TYR-22777", 2020, 5, 1);
			//System.out.print("\nBanco depois da inser��o\n");
			//bancoprovisorio.listarCarros();
			//bancoprovisorio.deletarCarro(4);
			//System.out.print("\nCarro Deletado\n");
			//bancoprovisorio.listarCarros();
			//bancoprovisorio.editarCarro(2,"FFT-20154", 2020, 2, 1);
			//System.out.print("\nCarro Alterado\n");
			//bancoprovisorio.listarCarros();
			
			bancoprovisorio.buscarModelo();
			//bancoprovisorio.inserirModelo(2, "HATCH",1);
			//System.out.print("\nDepois da inser��o do Modelo\n");
			//------------------------------------------------------
			//bancoprovisorio.editarModelo(1,"PIC_UP",1);
			//System.out.print("\nDepois da modifica��o do Modelo\n");
			//------------------------------------------------------
			//bancoprovisorio.deletarModelo(2);
			//System.out.print("\nDepois exclus�o do Modelo\n");
			
			//bancoprovisorio.buscarModelo();
			bancoprovisorio.desconectar();
		}else
			System.out.print("N�o foi poss�vel Conectar no Banco de Dados");
	} 
}
