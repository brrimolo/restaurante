package br.com.restaurante.restaurante.enums;

public enum TipoRefeicao {
    
    Entrada("Entrada"),
    PratoPrincipal("Prato principal"),
    Sobremesa("Sobremesa"),
    NaoAlcoolica("Bebida não alcoólica"),
    Alcoolica("Bebida alcoólica");
    
    private String tipo;

    // Método construtor
    TipoRefeicao(String tipo) {
        this.tipo = tipo;
    }
    
    public String getNome() {
        return this.tipo;
    }

}
