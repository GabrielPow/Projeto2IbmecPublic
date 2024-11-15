/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author gabri
 */
@Entity
@Table(name = "TB_CLIENTE", schema = "MenSab")
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CLIENTE")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCliente;
    @Column(name = "NOME_COMPLETO", nullable = false, unique = true, length = 50)
    private String nomec;
    @Column(name = "EMAIL", nullable = false, unique = true, length = 20)
    private String email;
    @OneToMany(mappedBy ="cliente")
    private List<Carrinho> carrinhos = new ArrayList<>();
    
    public Cliente() {
    
    }

    public Cliente(String nomec, String email) {
        this.nomec = nomec;
        this.email = email;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    
    public String getNomec() {
        return nomec;
    }

    public void setNomec(String nomec) {
        this.nomec = nomec;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Carrinho> getCarrinhos() {
        return carrinhos;
    }

    public void setCarrinhos(List<Carrinho> carrinhos) {
        this.carrinhos = carrinhos;
    }
    
    
    
    
    
    
}
