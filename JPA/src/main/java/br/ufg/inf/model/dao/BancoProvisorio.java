package br.ufg.inf.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.model.entidade.Carro;
import br.ufg.inf.model.entidade.Fabricante;
import br.ufg.inf.model.entidade.Modelo;

public class BancoProvisorio {
	static public List<Carro> carros = new ArrayList<Carro>();
	static public List<Modelo> mdelos = new ArrayList<Modelo>();
	static public List<Fabricante> fabricantes = new ArrayList<Fabricante>();
}
