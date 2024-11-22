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
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author gabri
 */

@Entity
@Table(name = "TB_MENSAGEM", schema = "MenSab")
public class Mensagen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PRODUTO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idMensagem;
    @Column(name = "NOME", nullable = false)
    private String nome;
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @Column(name = "CELULAR", nullable = false)
    private String celular;
    @Column(name = "ESTADO", nullable = false)
    private String estado;
    @Column(name = "CIDADE",nullable = false)
    private String cidade;
    @Column(name = "MENSAGEM",nullable = false)
    private String mensagem;
    
    public Mensagen() {
    
    }

    public Mensagen(String nome, String email, String celular, String estado, String cidade, String mensagem) {
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.estado = estado;
        this.cidade = cidade;
        this.mensagem = mensagem;
    }

    public UUID getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(UUID idMensagem) {
        this.idMensagem = idMensagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    
    

    
    
 
    
}
