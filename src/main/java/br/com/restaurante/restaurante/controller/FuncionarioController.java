package br.com.restaurante.restaurante.controller;

import java.util.List;

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

    @CrossOrigin
    @GetMapping("/")
    public List<Funcionario> getFuncionarios(){
        return funcionarioRepository.findAll();
    }


    @CrossOrigin
    @PostMapping("/incluir")
    public Long incluirFuncionario(@RequestBody Funcionario funcionario){
        funcionarioRepository.saveAndFlush(funcionario);
        return funcionario.getMatricula();
    }

    
    @CrossOrigin
    @PostMapping("/{id}")
    public void excluirFuncionario(@PathVariable("id") Long id) throws Exception{
        var x = funcionarioRepository.findById(id);

        if(x.isPresent()){
            Funcionario func = x.get();
            funcionarioRepository.delete(func);
        }else{
            throw new Exception("Funcionário não encontrado.");
        }
    }

    @CrossOrigin
    @PutMapping("/alterar/{id}")
    Funcionario alterarFuncionario(@PathVariable("id") Long id,@RequestBody Funcionario func){
        
        return funcionarioRepository.findById(id).map(funcionario -> {
            funcionario.setCargo(func.getCargo());
            funcionario.setCpf(func.getCpf());
            funcionario.setEmail(func.getEmail());
            funcionario.setEndereco(func.getEndereco());
            funcionario.setGerente(func.getGerente());
            funcionario.setLogin(func.getLogin());
            funcionario.setNome(func.getNome());
            funcionario.setSenha(func.getSenha());
            return funcionarioRepository.save(funcionario);
        }).orElseGet(() -> {
            func.setMatricula(id);
            return funcionarioRepository.save(func);
        });


    }

    
    @CrossOrigin
    @PostMapping("/login")
    public Funcionario login(@RequestBody IMFuncionarioLogin flogin){
        var funcionarios = funcionarioRepository.findAll();
        Funcionario funcionario = new Funcionario();
        for (Funcionario func : funcionarios) {
            if(func.getLogin().toLowerCase().equals(flogin.getLogin().toLowerCase())){
                if(func.getSenha().equals(flogin.getSenha())){
                    funcionario=func;
                    return funcionario;
                }
                
            }
        }
        return funcionario;
        
    }
}
