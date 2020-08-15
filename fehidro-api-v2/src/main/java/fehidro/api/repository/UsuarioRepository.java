package fehidro.api.repository;

import fehidro.api.model.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByLogin(String login);

	Usuario findByCPF(String cpf);

	List<Usuario> findAllByPerfilAcesso(Long perfilacesso);

}
