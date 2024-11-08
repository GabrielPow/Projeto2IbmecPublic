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
@Table(name = "TB_Produto", schema = "MenSab")
public class Produto implements Serializable {
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
    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;
    @Column(name = "STATUS", nullable = false)
    private String status;
    @Column(name = "DESTAQUE",nullable = false)
    private String emdestaque;
    @ManyToOne
    @JoinColumn(name = "CATEGORIA_ID",nullable = false)
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "IMAGEM_ID", nullable = false)
    private ImagemEntity imagem;
    //@ManyToOne
    //@JoinColumn(name = "TABELA_NUTRITIVA_ID", nullable = false)
    //private ImagemEntity tnutritiva;

    
    
    
    public Produto() {
    
    }

    public Produto(String nome, double percentual, String descricao) {
        this.nome = nome;
        this.percentual = percentual;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isStatus() {
        return "SIM".equals(status);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getEmdestaque() {
        return emdestaque;
    }

    public void setEmdestaque(String emdestaque) {
        this.emdestaque = emdestaque;
    }
    
    public boolean isDestaque() {
        return "SIM".equals(emdestaque);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ImagemEntity getImagem() {
        return imagem;
    }

    public void setImagem(ImagemEntity imagem) {
        this.imagem = imagem;
    }

    /*public ImagemEntity getTnutritiva() {
        return tnutritiva;
    }

    public void setTnutritiva(ImagemEntity tnutritiva) {
        this.tnutritiva = tnutritiva;
    }*/

    
    
}
