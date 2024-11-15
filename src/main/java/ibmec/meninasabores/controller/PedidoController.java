/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.controller;

import ibmec.meninasabores.model.Carrinho;
import ibmec.meninasabores.service.CarrinhoService;
import ibmec.meninasabores.service.ClienteService;
import ibmec.meninasabores.service.ProdutoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author gabri
 */
@Controller
@RequestMapping("/admin/carrinho")
public class PedidoController {
    
    @Autowired
    private CarrinhoService carrinhoService;
    
    @GetMapping("/pedidos")
    public String listar(ModelMap model) {
         List<Carrinho> carrinhos = carrinhoService.findAll();
         List<Carrinho> sortedCarrinhos = carrinhos.stream()
                 .filter(carrinho -> !"Comprando".equals(carrinho.getStatus()))
                 .collect(Collectors.toList());
         model.addAttribute("carrinhos", sortedCarrinhos);
         return "/carrinho/listar";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable UUID id, ModelMap model) {
         model.addAttribute("carrinho", carrinhoService.findById(id));
         return "/carrinho/editar";
    }
     
    @PostMapping("/atualizar")
    public String atualizar(@Valid @ModelAttribute Carrinho carrinho,
             BindingResult bindingResult, ModelMap model) {
         if (bindingResult.hasErrors()) {
             model.addAttribute("carrinho", carrinho);
             return "/carrinho/editar";
         }
         carrinhoService.update(carrinho);
         return "redirect:/admin/carrinho/pedidos";
    }
 
     
    @GetMapping("/remover/{id}")
    public String remover(@PathVariable UUID id, ModelMap model) {
         model.addAttribute("carrinho",carrinhoService.findById(id).orElseThrow(() ->
                 new RuntimeException("Carrinho não encontrado")));
         return "/carrinho/remover";
    }
     
    @PostMapping("/excluir/{id}")
    public String confirmarExclusao(@PathVariable UUID id, ModelMap model) {
         carrinhoService.deleteById(id);
         return "redirect:/admin/carrinho/pedidos";
     }
    
}
