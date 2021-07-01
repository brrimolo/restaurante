package br.com.restaurante.restaurante.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataHoraReserva;
    private LocalDate expiracaoReserva;
    private String nomeUsuario;
    private String telefone;
    private String qtdLugares;

    @OneToOne
    private Cliente cliente;
    
    @OneToOne
    private Mesa mesa;

    private String status;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getQtdLugares() {
        return qtdLugares;
    }

    public void setQtdLugares(String qtdLugares) {
        this.qtdLugares = qtdLugares;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //#region *** Getters and Setters ***
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataHoraReserva() {
        return dataHoraReserva;
    }

    public void setDataHoraReserva(LocalDate dataHoraReserva) {
        this.dataHoraReserva = dataHoraReserva;
    }

    public LocalDate getExpiracaoReserva() {
        return expiracaoReserva;
    }

    public void setExpiracaoReserva(LocalDate expiracaoReserva) {
        this.expiracaoReserva = expiracaoReserva;
    }
    //#endregion

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Reserva other = (Reserva) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    
    
}
