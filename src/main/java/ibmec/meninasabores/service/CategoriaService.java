/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.service;

import ibmec.meninasabores.model.Categoria;
import ibmec.meninasabores.repository.CategoriaRepository;
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
public class CategoriaService {
    
    
    @Autowired
    private CategoriaRepository categoriaRepository;


    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }
    
    public Optional<Categoria> findById(UUID id) {
        return categoriaRepository.findById(id);
    }

    public Optional<Categoria> findByNome(String nome) {
        return categoriaRepository.findByNome(nome);
    }

    public void deleteById(UUID id) {
        categoriaRepository.deleteById(id);
    }

    public Categoria update(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
}
