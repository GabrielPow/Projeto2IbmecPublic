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
import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author gabri
 */

@Entity
@Table(name = "TB_Carrinho", schema = "MenSab")
public class Carrinho implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PRODUTO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduto;
    @Column(name = "NOME", nullable = false, unique = true, length = 20)
    private String nome;
    @Column(name = "PRECO", nullable = false)
    private double percentual;
    @Column(name = "CATEGORIA", nullable = false)
    private String categoria;
    @Column(name = "STATUS", nullable = false)
    private String status;

    
    public Carrinho() {
    
    }
    
    public Carrinho(String nome, double percentual, String categoria, String status) {
        this.nome = nome;
        this.percentual = percentual;
        this.categoria = categoria;
        this.status = status;
    }

    public UUID getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(UUID idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPercentual() {
        return percentual;
    }

    public void setPercentual(double percentual) {
        this.percentual = percentual;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
}
