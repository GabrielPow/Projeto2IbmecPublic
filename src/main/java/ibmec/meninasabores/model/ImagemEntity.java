/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author gabri
 */
@Entity
@Table(name = "TB_IMAGEM", schema = "MenSab")
public class ImagemEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Lob
    private byte[] data;

    private String type;
    
    @OneToMany(mappedBy = "imagem")
    private List<Produto> produtosImagem;
    //@OneToMany(mappedBy = "tnutritiva")
    //private List<Produto> produtosTN;
    

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Produto> getProdutosImagem() {
        return produtosImagem;
    }

    public void setProdutosImagem(List<Produto> produtosImagem) {
        this.produtosImagem = produtosImagem;
    }

    /*public List<Produto> getProdutosTN() {
        return produtosTN;
    }

    public void setProdutosTN(List<Produto> produtosTN) {
        this.produtosTN = produtosTN;
    }*/
    
    
}