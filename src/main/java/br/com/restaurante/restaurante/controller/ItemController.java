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

import br.com.restaurante.restaurante.model.Item;
import br.com.restaurante.restaurante.repository.ItemRepository;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository){
        this.itemRepository=itemRepository;
    }

    @GetMapping("/")
    public List<Item> getItens(){
        return itemRepository.findAll();
    }

    @PostMapping("/incluir")
    public void incluirItem(@RequestBody Item item){
        itemRepository.save(item);
    }

    @GetMapping("/{id}")
    public void excluirItem(@PathVariable("id") Long id) throws Exception{
        var x = itemRepository.findById(id);

        if(x.isPresent()){
            Item item = x.get();
            itemRepository.delete(item);
        }else{
            throw new Exception("Item não encontrado.");
        }
    }


    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarItem(@PathVariable("id") Long id,@RequestBody Item item){
        itemRepository.findById(id)
        .map(x -> {
            x.setNome(item.getNome());
            x.setTipoRefeicao(item.getTipoRefeicao());
            x.setValor(item.getValor());
            Item itemAtualizado = itemRepository.save(x);
            return ResponseEntity.ok().body(itemAtualizado);
        }).orElse(ResponseEntity.notFound().build());
        return null;
    }
}
