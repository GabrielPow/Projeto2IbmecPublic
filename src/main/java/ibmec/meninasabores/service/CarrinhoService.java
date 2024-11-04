/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.service;

import ibmec.meninasabores.model.Produto;
import ibmec.meninasabores.repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gabri
 */

@Service
public class CarrinhoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;


    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }
    
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }
    
    public Optional<Produto> findById(UUID id) {
        return produtoRepository.findById(id);
    }

    public Optional<Produto> findByNome(String nome) {
        return produtoRepository.findByNome(nome);
    }

    public void deleteById(UUID id) {
        produtoRepository.deleteById(id);
    }

    public Produto update(Produto produto) {
        return produtoRepository.save(produto);
    }
}
