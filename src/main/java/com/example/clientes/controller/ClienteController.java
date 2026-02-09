package com.example.clientes.controller;

import java.util.Map;

import com.example.clientes.model.Cliente;
import com.example.clientes.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // GET todos
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.obtenerTodos();
    }

    // POST crear
    @PostMapping
    public Cliente crearCliente(@Valid @RequestBody Cliente cliente) {
        return clienteService.guardar(cliente);
    }

    @GetMapping("/{id}")
    public Cliente obtenerPorId(@PathVariable Long id) {
        return clienteService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
    }

    @PutMapping("/{id}")
    public Cliente actualizarCliente(
            @PathVariable Long id,
            @Valid @RequestBody Cliente cliente) {

        cliente.setId(id); // clave
        return clienteService.guardar(cliente);
    }

    @PatchMapping("/{id}")
    public Cliente actualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> campos) {

        Cliente cliente = clienteService.obtenerPorId(id);

        if (campos.containsKey("nombre")) {
            cliente.setNombre((String) campos.get("nombre"));
        }

        if (campos.containsKey("email")) {
            cliente.setEmail((String) campos.get("email"));
        }

        if (campos.containsKey("age")) {
            cliente.setAge((Integer) campos.get("age"));
        }

        if (campos.containsKey("active")) {
            cliente.setActive((Boolean) campos.get("active"));
        }

        return clienteService.guardar(cliente);
    }


}

