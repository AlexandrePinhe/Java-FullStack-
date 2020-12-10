package spring.ctrl.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import spring.model.entidades.Usuario;
import spring.model.repositorio.UsuarioRepositorio;

@Service
public class UsuarioNegocio {

	@Autowired
	private UsuarioRepositorio repositorio;

	public List<Usuario> findAllSimples() {
		System.out.println("negocio 1");

		return repositorio.findAll();
	}

	public Usuario findById(Integer id) {
		Optional<Usuario> retorno = repositorio.findById(id);
		return retorno.get();
	}

	public Usuario insert(Usuario usuario) {
		return repositorio.save(usuario);
	}

	public void delete(Integer id) {
		repositorio.deleteById(id);
	}

	public Usuario update(Usuario usuario) {
		Usuario usuarioUpd = repositorio.getOne(usuario.getIdUsuario());
		// Atualizar os daddos no objeto

		usuarioUpd.setNomeUsuario(usuario.getNomeUsuario());
		usuarioUpd.setLogin(usuario.getLogin());
		usuarioUpd.setNivel(usuario.getNivel());
		usuarioUpd.setSenha(usuario.getSenha());
		usuarioUpd.setDtNascimento(usuario.getDtNascimento());
		return repositorio.save(usuarioUpd);
	}

	public Page<Usuario> search(String searchTerm, int page, int size, String order, String active) {
		PageRequest pageRequest = PageRequest.of(page, size,
				(order.contentEquals("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC, active);
		return repositorio.search(searchTerm.toLowerCase(), pageRequest);
	}

	public Page<Usuario> findAll() {
		int page = 0;
		int size = 10;
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nomeUsuario");
		return new PageImpl<>(repositorio.findAll(), pageRequest, size);
	}

	public Usuario findByLogin(String login) {
		Usuario retorno = repositorio.findByLogin(login);
		return retorno;
	}
}
