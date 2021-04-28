package br.com.restaurante.restaurante.controller;

import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    
    @CrossOrigin
    @GetMapping("/")
    public List<Cliente> getClientes() {
        return clienteRepo.findAll();
    }

    @CrossOrigin
    @GetMapping("/login")
    public Cliente getClientePorEmail(@RequestBody Cliente clienteLogin){
        var clientes = clienteRepo.findAll();
        Cliente cliente = new Cliente();
        for (Cliente client : clientes) {
            if(client.getEmail().toLowerCase().equals(clienteLogin.getEmail().toLowerCase())){
                if(client.getSenha().equals(clienteLogin.getSenha())){
                    cliente.setCpf(client.getCpf());
                    cliente.setEmail(clienteLogin.getEmail());
                    cliente.setId(client.getId());
                    cliente.setEndereco(client.getEndereco());
                    client.setNome(client.getNome());
                    cliente.setSenha(client.getSenha());
                }else{
                    throw new BadRequestException("Senha inválida.");
                }
            }else{
                throw new NotFoundException("Email não cadastrado.");
            }
        }
        return cliente;
    }

    @CrossOrigin
    @PostMapping("/incluir")
    public void incluirCliente(@RequestBody Cliente cliente) {
        clienteRepo.save(cliente);
    }

    @CrossOrigin
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

    @CrossOrigin
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
