package com.pedidosclientes.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedidosclientes.Cliente.Cliente;

@RestController
@RequestMapping("/api")
public class Controller 
{
	@PostMapping("/nombreCliente/nuevo")
	public String[][] peticionPost(@RequestBody Cliente datoUsuario)
	{
		return datoUsuario.peticionCliente();
	}
	
	@GetMapping("/nombreCliente")
	public String[][] getClientes(@RequestBody Cliente obtenerDatos)
	{
		return obtenerDatos.getAll(); 
	}
	
	@DeleteMapping("/nombreCliente/{name}")
	public String deleteClientes(@RequestBody Cliente eliminarUsuario, @PathVariable String name)
	{
		return eliminarUsuario.removerCliente(name);
	}
}
