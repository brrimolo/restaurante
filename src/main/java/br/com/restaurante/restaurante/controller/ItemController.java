package br.com.restaurante.restaurante.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public Map<Item,Integer> carrinho = new HashMap<>();
    public int auxcarrinho;

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
    @PostMapping("/alterar/{id}")
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



    @CrossOrigin
    @GetMapping("/carrinho/add/{id}")
    public int carrinhoItens(@PathVariable("id") Long id) throws Exception{
        var item = itemRepository.findById(id);
        if(item.isPresent()){
            if(carrinho.containsKey(item.get())){
                System.out.println("Cheguei aqui");
                auxcarrinho = carrinho.get(item.get())+1;
                carrinho.remove(item.get());
                carrinho.put(item.get(), auxcarrinho);
                return carrinho.size();

            }else{
                carrinho.put(item.get(),1);
                return carrinho.size();
            }
            
        }else{
            throw new Exception("Item não disponível.");
        }
    }


    @CrossOrigin
    @GetMapping("/carrinho/remover/{id}")
    public int carrinhoItensRemover(@PathVariable("id") Long id) throws Exception{
        var item = itemRepository.findById(id);
        if(item.isPresent()){
            if(carrinho.containsKey(item.get())){
                auxcarrinho = carrinho.get(item.get())-1;
                carrinho.remove(item.get());
                if(auxcarrinho<=0){
                    return carrinho.size();
                }else{
                    carrinho.put(item.get(), auxcarrinho);
                }
                
                return carrinho.size();

            }else{
                //carrinho.put(item.get(),1);
                return carrinho.size();
            }
            
        }else{
            throw new Exception("Item não disponível.");
        }
    }


    @CrossOrigin
    @GetMapping("/carrinho")
    public String[][] getCarrinho() {
        List<Item> itensdocarrinhoaux = carrinho.keySet().stream().collect(Collectors.toList());
        List<Integer> quantidade = carrinho.values().stream().collect(Collectors.toList());
        String itensdocarrinho[][] = new String[itensdocarrinhoaux.size()][5];
        int i=0;
        for (Item item : itensdocarrinhoaux) {
            itensdocarrinho[i][0]=item.getId().toString();
            itensdocarrinho[i][1]=item.getNome();
            itensdocarrinho[i][2]=item.getTipoItem().toString();
            itensdocarrinho[i][3]=item.getValor().toString();
            itensdocarrinho[i][4]=quantidade.get(i).toString();
            i++;

        }

        return itensdocarrinho;

    }




    @CrossOrigin
    @GetMapping("/carrinho/valortotal")
    public Double valorTotalCarrinho(){
        Double valortotal=0.0;
        List<Item> itensdocarrinhoaux = carrinho.keySet().stream().collect(Collectors.toList());
        List<Integer> quantidade = carrinho.values().stream().collect(Collectors.toList());
        int i=0;
        for (Item item : itensdocarrinhoaux) {
            valortotal=valortotal+item.getValor()*quantidade.get(i);
            i++;        
        }
        return valortotal;
    }


    @CrossOrigin
    @GetMapping("/carrinho/totalitens")
    int getTotalItensCarrinho(){
        int totalitens=0;
        List<Integer> quantidade = carrinho.values().stream().collect(Collectors.toList());
        for (int item : quantidade) {
            totalitens+=item;
        }
        return totalitens;
    }

    @CrossOrigin
    @GetMapping("/carrinho/limpar")
    public int limparCarrinho() {
        carrinho.clear();
        return 0;
    }
    
}
