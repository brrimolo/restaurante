package br.com.restaurante.restaurante.model;

import javax.persistence.Entity;

@Entity
public class Telefone {

    private String ddd;
    private String numero;

    //#region *** Getters and Setters ***
    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    //#endregion

    @Override
    public String toString() {
        return "(" + ddd + ")" + numero;
    }
    
}
