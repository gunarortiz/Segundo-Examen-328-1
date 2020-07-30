package gunar.ortiz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import gunar.ortiz.repository.UsuarioRepository;
import gunar.ortiz.exception.ResourceNotFoundException;
import gunar.ortiz.model.Usuario;
@RestController
@RequestMapping("/")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	// get usuarios
	@GetMapping("usuarios")
	public List<Usuario> getAllUsuarios(){
		return this.usuarioRepository.findAll();
	}
	
	// get usuario by id
	@GetMapping("usuarios/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable(value = "id") Long usuarioId) 
			throws ResourceNotFoundException {
			Usuario usuario = usuarioRepository.findById(usuarioId)
			.orElseThrow(() -> new ResourceNotFoundException("No existe usuario "+usuarioId));
			return ResponseEntity.ok().body(usuario);
	}
	
	// creat usuario
	@PostMapping("usuarios")
	public Usuario createUsuario(@RequestBody Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	} 
	// update usuario
//	@PutMapping("usuarios/{id}")
//	public ResponseEntity<Usuario> updateUsuario(@PathVariable(value = "id") Long usuarioId, @RequestBody Usuario usuarioDetails ){
//		Usuario usuario = usuarioRepository.findById(usuarioId)
//				.orElseThrow(() -> new ResourceNotFoundException("No existe usuario "+usuarioId));
//		
//		usuario.setEmail(usuarioDetails.getEmail());
//		usuario.setNombre(usuarioDetails.getNombre());
//		usuario.setPassword(usuarioDetails.getPassword());
//		
//		return ResponseEntity.of(this.usuarioRepository.save(usuario));
//	}
	// delete  usuarioios
	@DeleteMapping("usuarios/{id}")
	public Map<String, Boolean> deleteUsuario(@PathVariable(value = "id") Long usuarioId) throws ResourceNotFoundException{
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new ResourceNotFoundException("No existe usuario "+usuarioId));
		this.usuarioRepository.delete(usuario);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}
} 
