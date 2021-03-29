package br.com.restaurante.restaurante.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.restaurante.restaurante.repository.ClienteRepository;
import br.com.restaurante.restaurante.model.Cliente;

@RestController
@RequestMapping("/cliente")
public class ClienteControl {
    
    private final ClienteRepository clienteRepo;

    public ClienteControl(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }
    
    @GetMapping("/")
    public List<Cliente> getClientes() {
        return clienteRepo.findAll();
    }

    @PostMapping("/")
    public void gravar(@RequestBody Cliente cliente) {
        clienteRepo.save(cliente);
    }

}
