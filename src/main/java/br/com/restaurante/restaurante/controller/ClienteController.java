package br.com.restaurante.restaurante.controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.restaurante.restaurante.repository.ClienteRepository;
import br.com.restaurante.restaurante.model.Cliente;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    
    private final ClienteRepository clienteRepo;

    public ClienteController(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }
    
    @GetMapping("/")
    public List<Cliente> getClientes() {
        return clienteRepo.findAll();
    }
    @GetMapping("/{cpf}")
    public Cliente getClientePorCpf(@PathVariable("cpf") String cpf){
        var clientes = clienteRepo.findAll();
        Cliente cliente = new Cliente();
        for (Cliente client : clientes) {
            if(client.getCpf().equals(cpf)){
                cliente.setCpf(cpf);
                cliente.setEmail(client.getEmail());
                cliente.setId(client.getId());
                cliente.setEndereco(client.getEndereco());
                client.setNome(client.getNome());
                client.setTelefone(client.getTelefone());

            }
        }
        return cliente;
    }

    @PostMapping("/incluir")
    public void incluirCliente(@RequestBody Cliente cliente) {
        clienteRepo.save(cliente);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarCliente(@PathVariable("id") Long id,@RequestBody Cliente cliente){
        clienteRepo.findById(id)
        .map(x -> {
            x.setCpf(cliente.getCpf());
            x.setEmail(cliente.getEmail());
            x.setEndereco(cliente.getEndereco());
            x.setNome(cliente.getNome());
            x.setTelefone(cliente.getTelefone());
            Cliente cliAtualizado = clienteRepo.save(x);
            return ResponseEntity.ok().body(cliAtualizado);
        }).orElse(ResponseEntity.notFound().build());
        return null;
    }

    @GetMapping("/{id}")
    public void excluirCliente(@PathVariable("id") Long id) throws Exception{
        var x = clienteRepo.findById(id);

        if(x.isPresent()){
            Cliente cliente = x.get();
            clienteRepo.delete(cliente);
        }else{
            throw new Exception("Cliente não encontrado.");
        }
    }
}
