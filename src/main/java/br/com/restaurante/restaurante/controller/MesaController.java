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

import br.com.restaurante.restaurante.model.Mesa;
import br.com.restaurante.restaurante.repository.MesaRepository;

@RestController
@RequestMapping("/mesa")
public class MesaController {
    private final MesaRepository mesaRepository;

    public MesaController (MesaRepository mesaRepository){
        this.mesaRepository=mesaRepository;
    }

    @CrossOrigin
    @GetMapping("/")
    public List<Mesa> getMesas(){
        return mesaRepository.findAll();
    }

    @CrossOrigin
    @PostMapping("/incluir")
    public void incluirMesa(@RequestBody Mesa mesa){
        mesaRepository.save(mesa);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public void excluirMesa(@PathVariable("id") Long id) throws Exception{
        var x = mesaRepository.findById(id);

        if(x.isPresent()){
            Mesa mesa = x.get();
            mesaRepository.delete(mesa);
        }else{
            throw new Exception("Mesa não encontrado.");
        }
    }

    @CrossOrigin
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarMesa(@PathVariable("id") Long id,@RequestBody Mesa mesa){
        mesaRepository.findById(id)
        .map(x -> {
            x.setQtdCadeiras(mesa.getQtdCadeiras());
            x.setReservada(mesa.getReservada());
            Mesa mesaAtualizado = mesaRepository.save(x);
            return ResponseEntity.ok().body(mesaAtualizado);
        }).orElse(ResponseEntity.notFound().build());
        return null;
    }
}
