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
import jakarta.persistence.Table;
import java.util.UUID;

/**
 *
 * @author gabri
 */
@Entity
@Table(name = "TB_Cliente", schema = "MenSab")
public class Cliente {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_Cliente")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCliente;
    @Column(name = "NOME", nullable = false, unique = true, length = 20)
    private String nome;
    
    public Cliente() {
    
    }

    public Cliente(UUID idCliente, String nome) {
        this.idCliente = idCliente;
        this.nome = nome;
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
}
