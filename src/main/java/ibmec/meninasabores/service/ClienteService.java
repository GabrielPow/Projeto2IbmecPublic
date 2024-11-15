/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.service;

import ibmec.meninasabores.model.Cliente;
import ibmec.meninasabores.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gabri
 */
@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
    
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }
    
    public Optional<Cliente> findByNomec(String nomec) {
        return clienteRepository.findByNomec(nomec);
    }
    
    
}
