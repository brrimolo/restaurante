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

import br.com.restaurante.restaurante.model.Telefone;
import br.com.restaurante.restaurante.repository.TelefoneRepository;

@RestController
@RequestMapping("telefone")
public class TelefoneController {
    private final TelefoneRepository telefoneRepository;

    public TelefoneController(TelefoneRepository telefoneRepository){
        this.telefoneRepository=telefoneRepository;
    }

    @GetMapping("/")
    public List<Telefone> getTelefones(){
        return telefoneRepository.findAll();
    }

    @PostMapping("/incluir")
    public void incluirTelefone(@RequestBody Telefone telefone){
        telefoneRepository.save(telefone);
    }

    @GetMapping("/{id}")
    public void excluirTelefone(@PathVariable("id") Long id) throws Exception{
        var x = telefoneRepository.findById(id);

        if(x.isPresent()){
            Telefone tel = x.get();
            telefoneRepository.delete(tel);
        }else{
            throw new Exception("Telefone não encontrado.");
        }
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarTelefone(@PathVariable("id") Long id,@RequestBody Telefone tel){
        telefoneRepository.findById(id)
        .map(x -> {
            x.setDdd(tel.getDdd());
            x.setNumero(tel.getNumero());
            Telefone telAtualizado = telefoneRepository.save(x);
            return ResponseEntity.ok().body(telAtualizado);
        }).orElse(ResponseEntity.notFound().build());
        return null;
    }
}
