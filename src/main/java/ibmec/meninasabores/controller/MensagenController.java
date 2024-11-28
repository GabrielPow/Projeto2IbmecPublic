/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.controller;

import ibmec.meninasabores.model.Mensagen;
import ibmec.meninasabores.service.MensagenService;
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
@RequestMapping("/admin/mensagem")
public class MensagenController {
    
    @Autowired
    private MensagenService mensagenService;
    
    @GetMapping("/listar")
    public String listar(ModelMap model) {
     List<Mensagen> mensagens = mensagenService.findAll();
     List<Mensagen> sortedMensagens = mensagens.stream()
         .sorted((produto1, produto2) -> produto1.getNome().compareTo(produto2.getNome()))
         .collect(Collectors.toList());
     model.addAttribute("mensagens", sortedMensagens);
     return "mensagen/lista_mensagens";
     }
     
    @GetMapping("/ver/{id}")
    public String ver(@PathVariable UUID id, ModelMap model) {
     model.addAttribute("mensagem", mensagenService.findById(id));
     return "mensagen/editar_categoria";
     }
    
    @GetMapping("/remover/{id}")
    public String remover(@PathVariable UUID id, ModelMap model) {
         model.addAttribute("mensagem",mensagenService.findById(id).orElseThrow(() ->
                 new RuntimeException("Mensagen não encontrado")));
         return "mensagen/remover";
    }
     
    @PostMapping("/excluir/{id}")
    public String confirmarExclusao(@PathVariable UUID id, ModelMap model) {
         mensagenService.deleteById(id);
         return "redirect:/admin/mensagem/listar";
    }
    
}
