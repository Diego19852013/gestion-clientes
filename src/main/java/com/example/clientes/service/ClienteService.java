package com.example.clientes.service;
import jakarta.validation.constraints.NotNull;

import com.example.clientes.model.Cliente;
import com.example.clientes.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public Cliente guardar(Cliente cliente) {
        return clienteRepository.saveAndFlush(cliente); //persiste  y sincroniza con la base de datos
    }

    public Cliente obtenerPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public void eliminar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente no existe");
        }
        clienteRepository.deleteById(id);
    }

    public Cliente actualizar(Long id, Cliente datos) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setNombre(datos.getNombre());
        cliente.setEmail(datos.getEmail());
        cliente.setAge(datos.getAge());
        cliente.setActive(datos.getActive());

        return clienteRepository.saveAndFlush(cliente);
    }

}

