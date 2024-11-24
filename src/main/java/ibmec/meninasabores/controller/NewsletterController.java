/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.controller;

import ibmec.meninasabores.model.Newsletter;
import ibmec.meninasabores.service.NewsletterService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author gabri
 */
@Controller
@RequestMapping("/admin/newsletter")
public class NewsletterController {
    
    @Autowired
    private NewsletterService newsletterService;
    
    @GetMapping("/listar")
    public String listar(ModelMap model) {
     List<Newsletter> newsletters = newsletterService.findAll();
     List<Newsletter> sortedNewsletters = newsletters.stream()
         .collect(Collectors.toList());
     String todoEmails = ColetarEmail(newsletters);
     model.addAttribute("newsletters", sortedNewsletters);
     model.addAttribute("lista_email",todoEmails);
     return "newsletter/lista_newsletter";
     }
    
    private String ColetarEmail(List<Newsletter> newsletters) {
      return newsletters.stream().map(Newsletter::getEmail).collect(Collectors.joining(", "));
    }
    
    @GetMapping("/remover/{id}")
    public String remover(@PathVariable UUID id, ModelMap model) {
         model.addAttribute("newsletter",newsletterService.findById(id).orElseThrow(() ->
                 new RuntimeException("Newsletter não encontrado")));
         return "/newsletter/remover";
    }
     
    @PostMapping("/excluir/{id}")
    public String confirmarExclusao(@PathVariable UUID id, ModelMap model) {
         newsletterService.deleteById(id);
         return "redirect:/admin/newsletter/listar";
    }
    
}
