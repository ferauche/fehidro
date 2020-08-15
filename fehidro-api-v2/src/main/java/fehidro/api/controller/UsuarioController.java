package fehidro.api.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import fehidro.api.model.Usuario;
import fehidro.api.repository.UsuarioRepository;
import fehidro.api.util.password.Password;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository _usuarioRepository;
	
	@GetMapping
	public List<Usuario> getAll() {		
		return _usuarioRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Usuario get(@PathVariable(value = "id") Long id) {
		return _usuarioRepository.findById(id).get();
	}
	
	@GetMapping("/obterPorLogin/{login}")
	public Usuario obterPorLogin(@PathVariable(value = "login") String login) {
		return _usuarioRepository.findByLogin(login);
	}
	
	@GetMapping("/obterPorCPF/{cpf}")
	public Usuario obterPorCPF(@PathVariable(value = "cpf") String cpf) {
		return _usuarioRepository.findByCPF(cpf);
	}
	
	@GetMapping("/obterPorPerfilAcesso/{perfilacesso}")
	public List<Usuario> obterPorPerfilAcesso(@PathVariable(value = "perfilacesso") Long perfilacesso) {
		return _usuarioRepository.findAllByPerfilAcesso(perfilacesso);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> add(@RequestBody Usuario usuario, UriComponentsBuilder uriBuilder) {
		
		try {
			usuario.setLogin();
			usuario.setAtivo();	
			String senha = Password.generateRandomPassword(10);
			usuario.setSenha(Password.hashPassword(senha));
			Usuario user = _usuarioRepository.save(usuario);
			//TODO: migrar para spring-boot-starter-mail
			//EmailUtil.sendMail(usuario.getEmail(), usuario.getNome(), usuario.getLogin(), senha);
			URI uri = uriBuilder.path("/{id}").buildAndExpand(user.getId()).toUri();
			return ResponseEntity.created(uri).body(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@PutMapping
	public Usuario update(@RequestBody Usuario usuario) {
		return _usuarioRepository.save(usuario);	
	}
	
}
