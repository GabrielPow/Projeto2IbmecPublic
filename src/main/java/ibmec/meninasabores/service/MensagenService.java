/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.service;

import ibmec.meninasabores.model.Mensagen;
import ibmec.meninasabores.repository.MensagenRepository;
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
public class MensagenService {
    
    
    @Autowired
    private MensagenRepository mensagenRepository;


    public Mensagen save(Mensagen mensagen) {
        return mensagenRepository.save(mensagen);
    }
    
    public List<Mensagen> findAll() {
        return mensagenRepository.findAll();
    }
    
    public Optional<Mensagen> findById(UUID id) {
        return mensagenRepository.findById(id);
    }

    public void deleteById(UUID id) {
        mensagenRepository.deleteById(id);
    }

    public Mensagen update(Mensagen mensagen) {
        return mensagenRepository.save(mensagen);
    }
    
    public long count() {
        return mensagenRepository.count();
    }
}
