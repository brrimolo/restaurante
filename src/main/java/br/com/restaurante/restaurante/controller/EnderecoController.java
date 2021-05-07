package br.com.restaurante.restaurante.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.restaurante.restaurante.model.Endereco;
import br.com.restaurante.restaurante.repository.EnderecoRepository;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    private final EnderecoRepository enderecoRepository;

    public EnderecoController(EnderecoRepository enderecoRepository){
        this.enderecoRepository=enderecoRepository;
    }


    @CrossOrigin
    @GetMapping("/")
    public List<Endereco> getEndereco(){
        return enderecoRepository.findAll();
    }

    @CrossOrigin
    @PostMapping("/incluir")
    public void incluirEndereco(@RequestBody Endereco endereco){
        enderecoRepository.save(endereco);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public void excluirEndereco(@PathVariable("id") Long id) throws Exception{
        var x = enderecoRepository.findById(id);

        if(x.isPresent()){
            Endereco endereco = x.get();
            enderecoRepository.delete(endereco);
        }else{
            throw new Exception("Endereço não encontrado.");
        }
    }

    @CrossOrigin
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarEndereco(@PathVariable("id") Long id,@RequestBody Endereco endereco){
        enderecoRepository.findById(id)
        .map(x -> {
            x.setBairro(endereco.getBairro());
            x.setCep(endereco.getCep());
            x.setCidade(endereco.getCidade());
            x.setComplemento(endereco.getComplemento());
            x.setLogradouro(endereco.getLogradouro());
            x.setNumero(endereco.getNumero());
            x.setUf(endereco.getUf());
            Endereco endAtual = enderecoRepository.save(x);
            return ResponseEntity.ok().body(endAtual);
        }).orElse(ResponseEntity.notFound().build());
        return null;
    }

}
