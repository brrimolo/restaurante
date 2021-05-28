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

import br.com.restaurante.restaurante.model.Bairro;
import br.com.restaurante.restaurante.repository.BairroRepository;

@RestController
@RequestMapping("/bairro")
public class BairroController {
    
    private final BairroRepository bairroRepository;

    public BairroController(BairroRepository bairroRepository){
        this.bairroRepository = bairroRepository;
    }

    @CrossOrigin
    @GetMapping("/")
    public List<Bairro> getBairros(){
        return bairroRepository.findAll();
    }

    @CrossOrigin
    @PostMapping("/incluir")
    public void incluirBairro(@RequestBody Bairro bairro){
        bairroRepository.save(bairro);
    }

    @CrossOrigin
    @PostMapping("/excluir/{id}")
    public void excluirBairro(@PathVariable("id") Long id) throws Exception{
        var x = bairroRepository.findById(id);

        if(x.isPresent()){
            Bairro bairro = x.get();
            bairroRepository.delete(bairro);
        }else{
            throw new Exception("Bairro não encontrado.");
        }
    }


    @CrossOrigin
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarBairro(@PathVariable("id") Long id,@RequestBody Bairro bairro){
        bairroRepository.findById(id)
        .map(x -> {
            x.setNome(bairro.getNome());
            x.setValorFrete(bairro.getValorFrete());
            Bairro bairrAtualizado = bairroRepository.save(x);
            return ResponseEntity.ok().body(bairrAtualizado);
        }).orElse(ResponseEntity.notFound().build());
        return null;
    }

}
