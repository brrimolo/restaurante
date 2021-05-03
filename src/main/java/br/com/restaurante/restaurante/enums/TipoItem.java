package br.com.restaurante.restaurante.enums;

public enum TipoItem {
    
    Entrada(1),
    PratoPrincipal(2),
    Sobremesa(3),
    NaoAlcoolica(4),
    Alcoolica(5);
    
    public int tipo;

    // Método construtor
    TipoItem(int tipo) {
        this.tipo = tipo;
    }


    public int getItem(){
        return this.tipo;
    }
}
