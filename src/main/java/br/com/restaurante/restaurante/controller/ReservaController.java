package br.com.restaurante.restaurante.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.restaurante.restaurante.model.Reserva;
import br.com.restaurante.restaurante.repository.ReservaRepository;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    private final ReservaRepository reservaRepository;

    public ReservaController(ReservaRepository reservaRepository ){
        this.reservaRepository=reservaRepository;
    }

    @CrossOrigin
    @GetMapping("/")
    public List<Reserva> getReservas(){
        return reservaRepository.findAll();
    }

    @CrossOrigin
    @PostMapping("/incluir")
    public void incluirReserva(@RequestBody Reserva reserva){
        LocalDateTime expiracao = reserva.getDataHoraReserva();
        reserva.setExpiracaoReserva(expiracao.plusMinutes(30));
        reservaRepository.save(reserva);
    }


    @CrossOrigin
    @GetMapping("/{id}")
    public void excluirReserva(@PathVariable("id") Long id) throws Exception{
        var x = reservaRepository.findById(id);

        if(x.isPresent()){
            Reserva reserva = x.get();
            reservaRepository.delete(reserva);
        }else{
            throw new Exception("Reserva não encontrado.");
        }
    }

    @CrossOrigin
    @PostMapping("/alterar/{id}")
    public ResponseEntity<String> alterarReserva(@PathVariable("id") Long id,@RequestBody String status){
        
        reservaRepository.findById(id)
        .map(x -> {
            x.setStatus(status);
            Reserva reservaAtualizada = reservaRepository.save(x);
            return ResponseEntity.ok().body(reservaAtualizada);
        }).orElse(ResponseEntity.notFound().build());
        return null;
    }
}
