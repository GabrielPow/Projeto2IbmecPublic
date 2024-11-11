/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.service;

import ibmec.meninasabores.model.Carrinho;
import ibmec.meninasabores.repository.CarrinhoRepository;
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
    private CarrinhoRepository carrinhoRepository;


    public Carrinho save(Carrinho carrinho) {
        return carrinhoRepository.save(carrinho);
    }
    
    public List<Carrinho> findAll() {
        return carrinhoRepository.findAll();
    }
    
    public Optional<Carrinho> findById(UUID id) {
        return carrinhoRepository.findById(id);
    }

    public void deleteById(UUID id) {
        carrinhoRepository.deleteById(id);
    }

    public Carrinho update(Carrinho carrinho) {
        return carrinhoRepository.save(carrinho);
    }
}
