/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.service;

import ibmec.meninasabores.model.Newsletter;
import ibmec.meninasabores.repository.NewsletterRepository;
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
public class NewsletterService {
    
    
    @Autowired
    private NewsletterRepository newsletterRepository;


    public Newsletter save(Newsletter newsletter) {
        return newsletterRepository.save(newsletter);
    }
    
    public List<Newsletter> findAll() {
        return newsletterRepository.findAll();
    }
    
    public Optional<Newsletter> findById(UUID id) {
        return newsletterRepository.findById(id);
    }

    public void deleteById(UUID id) {
        newsletterRepository.deleteById(id);
    }

    public Newsletter update(Newsletter newsletter) {
        return newsletterRepository.save(newsletter);
    }
    
    public long count() {
        return newsletterRepository.count();
    }
}
