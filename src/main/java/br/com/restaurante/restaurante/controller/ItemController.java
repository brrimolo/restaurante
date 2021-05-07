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

import br.com.restaurante.restaurante.enums.TipoItem;
import br.com.restaurante.restaurante.model.Item;
import br.com.restaurante.restaurante.repository.ItemRepository;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository){
        this.itemRepository=itemRepository;
    }

    @CrossOrigin
    @GetMapping("/")
    public List<Item> getItens(){
        return itemRepository.findAll();
    }

    @CrossOrigin
    @PostMapping("/incluir")
    public void incluirItem(@RequestBody Item item){
        
        itemRepository.save(item);
    }

    @CrossOrigin
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


    @CrossOrigin
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarItem(@PathVariable("id") Long id,@RequestBody Item item){
        itemRepository.findById(id)
        .map(x -> {
            x.setNome(item.getNome());
            x.setTipoItem(item.getTipoItem());
            x.setValor(item.getValor());
            x.setQtdRefeicao(item.getQtdRefeicao());
            x.setTempoPreparo(item.getTempoPreparo());
            Item itemAtualizado = itemRepository.save(x);
            return ResponseEntity.ok().body(itemAtualizado);
        }).orElse(ResponseEntity.notFound().build());
        return null;
    }


    @CrossOrigin
    @GetMapping("/tipoitem")
    public TipoItem[] getItemTipos(){
        var x = TipoItem.values();
        return x;

    }
}
