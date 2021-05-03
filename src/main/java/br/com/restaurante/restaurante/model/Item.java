package br.com.restaurante.restaurante.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.restaurante.restaurante.enums.TipoItem;

@Entity
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private TipoItem tipoItem;
    private Double valor;
    private Integer qtdRefeicao;
    private Double tempoPreparo;

    //#region *** Getters and Setters ***
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    //#endregion

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((qtdRefeicao == null) ? 0 : qtdRefeicao.hashCode());
        result = prime * result + ((tempoPreparo == null) ? 0 : tempoPreparo.hashCode());
        result = prime * result + ((tipoItem == null) ? 0 : tipoItem.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (qtdRefeicao == null) {
            if (other.qtdRefeicao != null)
                return false;
        } else if (!qtdRefeicao.equals(other.qtdRefeicao))
            return false;
        if (tempoPreparo == null) {
            if (other.tempoPreparo != null)
                return false;
        } else if (!tempoPreparo.equals(other.tempoPreparo))
            return false;
        if (tipoItem != other.tipoItem)
            return false;
        if (valor == null) {
            if (other.valor != null)
                return false;
        } else if (!valor.equals(other.valor))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }

    public TipoItem getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(TipoItem tipoItem) {
        this.tipoItem = tipoItem;
    }

    public Integer getQtdRefeicao() {
        return qtdRefeicao;
    }

    public void setQtdRefeicao(Integer qtdRefeicao) {
        this.qtdRefeicao = qtdRefeicao;
    }

    public Double getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(Double tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    

}
