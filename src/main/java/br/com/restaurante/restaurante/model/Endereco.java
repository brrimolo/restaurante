package br.com.restaurante.restaurante.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import br.com.restaurante.restaurante.enums.TipoUf;

@Entity
public class Endereco {

    private String logradouro;
    private String numero;
    private String complemento;
    @OneToOne
    private Bairro bairro;
    private String cidade;
    private TipoUf uf;
    private String cep;

    //#region *** Getters and Setters ***
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public TipoUf getUf() {
        return uf;
    }

    public void setUf(TipoUf uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    //#endregion
    

}
