package fehidro.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import fehidro.api.model.SubcriterioAvaliacao;
import fehidro.api.repository.SubcriterioAvaliacaoRepository;

@RestController
@RequestMapping("/subcriterioAvaliacao")
public class SubcriterioAvaliacaoController {
	@Autowired
	private SubcriterioAvaliacaoRepository _subcriterioAvaliacaoRepository; 

	@GetMapping
	public ResponseEntity<List<SubcriterioAvaliacao>> getAll() {		
		return ResponseEntity.ok(_subcriterioAvaliacaoRepository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<SubcriterioAvaliacao> add(@RequestBody SubcriterioAvaliacao subcriterio, UriComponentsBuilder uriBuilder) {
		SubcriterioAvaliacao cadastrado = _subcriterioAvaliacaoRepository.save(subcriterio);
		URI uri = uriBuilder.path("/{id}").buildAndExpand(cadastrado.getId()).toUri();
		return ResponseEntity.created(uri).body(cadastrado);
	}

	@PutMapping
	public ResponseEntity<SubcriterioAvaliacao> update(@RequestBody SubcriterioAvaliacao subcriterio) {
		SubcriterioAvaliacao cadastrado =  _subcriterioAvaliacaoRepository.save(subcriterio);
		return ResponseEntity.ok(cadastrado);
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		Optional<SubcriterioAvaliacao> subcriterio = _subcriterioAvaliacaoRepository.findById(id);
		if (subcriterio.isPresent()) {
			_subcriterioAvaliacaoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
