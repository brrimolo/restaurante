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

import br.com.restaurante.restaurante.model.Pedido;
import br.com.restaurante.restaurante.repository.PedidoRepository;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    private final PedidoRepository pedidoRepository;

    public PedidoController(PedidoRepository pedidoRepository){
        this.pedidoRepository=pedidoRepository;
    }

    @GetMapping("/")
    public List<Pedido> getPedidos(){
        return pedidoRepository.findAll();
    }

    @PostMapping("/incluir")
    public void incluirPedido(@RequestBody Pedido pedido){
        pedidoRepository.save(pedido);
    }

    @GetMapping("/{id}")
    public void excluirPedido(@PathVariable("id") Long id) throws Exception{
        var x = pedidoRepository.findById(id);

        if(x.isPresent()){
            Pedido pedido = x.get();
            pedidoRepository.delete(pedido);
        }else{
            throw new Exception("Pedido não encontrado.");
        }
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarPedido(@PathVariable("id") Long id,@RequestBody Pedido pedido){
        pedidoRepository.findById(id)
        .map(x -> {
            x.setDataHoraPedido(pedido.getDataHoraPedido());
            x.setDelivery(pedido.getDelivery());
            x.setPrevisaoEntrega(pedido.getPrevisaoEntrega());
            x.setValorTotal(pedido.getValorTotal());
            Pedido pedidoAtualizado = pedidoRepository.save(x);
            return ResponseEntity.ok().body(pedidoAtualizado);
        }).orElse(ResponseEntity.notFound().build());
        return null;
    }
}
