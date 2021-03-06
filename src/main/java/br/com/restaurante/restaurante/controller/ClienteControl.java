package br.com.restaurante.restaurante.controller;

import br.com.restaurante.restaurante.repository.ClienteRepo;

public class ClienteControl {
    
    private final ClienteRepo clienteRepo;

    public ClienteControl(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }
    
}
