package fehidro.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fehidro.model.Usuario;

public class SessionContext {
	private static SessionContext instance = null;

	private SessionContext() {
	}

	public static SessionContext getInstance() {
		if (instance == null)
			instance = new SessionContext();
		return instance;
	}

	private ExternalContext currentExternalContext() throws RuntimeException {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context == null) {
			throw new RuntimeException("O FacesContext n�o pode ser chamado fora de uma requisi��o HTTP");
		} else {
			return context.getExternalContext();
		}
	}

	public Object getAttribute(String nome) throws RuntimeException {
		HttpSession session = (HttpSession) currentExternalContext().getSession(false);
		if (session == null) {
			throw new RuntimeException("N�o foi poss�vel recuperar a sess�o!");
		}

		return session.getAttribute(nome);
	}

	public void setAttribute(String nome, Object valor) throws RuntimeException {
		HttpSession session = (HttpSession) currentExternalContext().getSession(false);
		if (session == null) {
			throw new RuntimeException("N�o foi poss�vel recuperar a sess�o!");
		}
		session.setAttribute(nome, valor);
	}
	
	public Usuario usuarioLogado() {
		Usuario user = (Usuario) getAttribute("usuarioLogado");
		return user;
	}

	public void encerrarSessao() {
		currentExternalContext().invalidateSession();
	}
}
