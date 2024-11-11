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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "TB_Carrinho", schema = "MenSab")
public class Carrinho implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CARRINHO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCarrinho;
    @Column(name = "PRECO", nullable = false)
    private double percentual;
    @Column(name = "STATUS", nullable = false)
    private String status;
    @ManyToMany
    @JoinTable(
    name = "CARRINHO_PRODUTOS",
    joinColumns = @JoinColumn(name = "CARRINHO_ID"),
    inverseJoinColumns = @JoinColumn(name = "PRODUTO_ID")
    )
    private List<Produto> cProdutos = new ArrayList<>();

    
    public Carrinho() {
    
    }
    
    public Carrinho(double percentual, String categoria, String status) {
        this.percentual = percentual;
        this.status = status;
    }

    public UUID getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(UUID idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    public double getPercentual() {
        return percentual;
    }

    public void setPercentual(double percentual) {
        this.percentual = percentual;
    }
    
    public void addPercentual(double percentual) {
        this.percentual = this.percentual + percentual;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Produto> getcProdutos() {
        return cProdutos;
    }

    public void setcProdutos(List<Produto> cProdutos) {
        this.cProdutos = cProdutos;
    }

    
    
}
