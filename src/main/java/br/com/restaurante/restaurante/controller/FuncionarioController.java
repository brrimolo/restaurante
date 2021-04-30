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

import br.com.restaurante.restaurante.model.Funcionario;
import br.com.restaurante.restaurante.model.InputModel.IMFuncionarioLogin;
import br.com.restaurante.restaurante.repository.FuncionarioRepository;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioController(FuncionarioRepository funcionarioRepository){
        this.funcionarioRepository=funcionarioRepository;
    }

    @GetMapping("/")
    public List<Funcionario> getFuncionarios(){
        return funcionarioRepository.findAll();
    }

    @PostMapping("/incluir")
    public void incluirFuncionario(@RequestBody Funcionario funcionario){
        funcionarioRepository.saveAndFlush(funcionario);
    }

    
    @GetMapping("/{id}")
    public void excluirFuncionario(@PathVariable("id") Long id) throws Exception{
        var x = funcionarioRepository.findById(id);

        if(x.isPresent()){
            Funcionario func = x.get();
            funcionarioRepository.delete(func);
        }else{
            throw new Exception("Funcionário não encontrado.");
        }
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarFuncionario(@PathVariable("id") Long id,@RequestBody Funcionario func){
        funcionarioRepository.findById(id)
        .map(x -> {
            x.setCargo(func.getCargo());
            x.setCpf(func.getCpf());
            x.setEmail(func.getEmail());
            x.setEndereco(func.getEndereco());
            x.setGerente(func.getGerente());
            x.setLogin(func.getLogin());
            x.setSenha(func.getSenha());
            x.setNome(func.getNome());
            x.setTelefone(func.getTelefone());
            x.setMatricula(func.getMatricula());
            Funcionario funcatualizado = funcionarioRepository.save(x);
            return ResponseEntity.ok().body(funcatualizado);
        }).orElse(ResponseEntity.notFound().build());
        return null;
    }

    
    @CrossOrigin
    @GetMapping("/login")
    public Funcionario login(@RequestBody IMFuncionarioLogin flogin){
        var funcionarios = funcionarioRepository.findAll();
        Funcionario funcionario = new Funcionario();
        for (Funcionario func : funcionarios) {
            if(func.getLogin().toLowerCase().equals(flogin.getLogin().toLowerCase())){
                if(func.getSenha().equals(flogin.getSenha())){
                    funcionario=func;
                    return funcionario;
                }
                return null;
            }
        }
        return null;
        
    }
}
