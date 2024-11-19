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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabri
 */
@Entity
@Table(name = "TB_Pedidos", schema = "MenSab")
public class Pedidos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PEDIDO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPedido;
    @Column(name = "NOME_COMPLETO", nullable = false, length = 50)
    private String nomec;
    @Column(name = "STATUS", nullable = false, length = 20)
    private String status;
    @Column(name = "PRODUTOS_NOME", nullable = false)
    private List<String> nomeProdutos = new ArrayList<>();
    @Column(name = "PRODUTOS_PRECO", nullable = false)
    private List<Double> precoProdutos = new ArrayList<>();
    @Column(name = "PRECO TOTAL", nullable = false)
    private double preco;
    
    public Pedidos() {
    
    }

    public Pedidos(String nomec, String email, double preco, String status) {
        this.status = status;
        this.nomec = nomec;
        this.preco = preco;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public String getNomec() {
        return nomec;
    }

    public void setNomec(String nomec) {
        this.nomec = nomec;
    }

    public List<String> getNomeProdutos() {
        return nomeProdutos;
    }

    public void setNomeProdutos(List<String> nomeProdutos) {
        this.nomeProdutos = nomeProdutos;
    }

    public List<Double> getPrecoProdutos() {
        return precoProdutos;
    }

    public void setPrecoProdutos(List<Double> precoProdutos) {
        this.precoProdutos = precoProdutos;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
    
}
