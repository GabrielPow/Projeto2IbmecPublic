/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.controller;

import ibmec.meninasabores.model.Pedidos;
import ibmec.meninasabores.service.PedidosService;
import jakarta.validation.Valid;
import java.util.List;
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

/**
 *
 * @author gabri
 */
@Controller
@RequestMapping("/admin/carrinho")
public class PedidoController {
    
    @Autowired
    private PedidosService pedidoService;
    
    @GetMapping("/pedidos")
    public String listar(ModelMap model) {
         List<Pedidos> pedidos = pedidoService.findAll();
         List<Pedidos> sortedCarrinhos = pedidos.stream()
                 .collect(Collectors.toList());
         model.addAttribute("pedidos", sortedCarrinhos);
         return "/carrinho/lista_carrinho";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, ModelMap model) {
        Pedidos pedido = pedidoService.findById(id).orElse(null);
         model.addAttribute("pedido", pedido);
         return "/carrinho/editar_carrinho";
    }
     
    @PostMapping("/atualizar")
    public String atualizar(@Valid @ModelAttribute Pedidos pedido,
             BindingResult bindingResult, ModelMap model) {
         if (bindingResult.hasErrors()) {
             model.addAttribute("pedido", pedido);
             return "/carrinho/editar_carrinho";
         }
         pedidoService.update(pedido);
         return "redirect:/admin/carrinho/pedidos";
    }
 
     
    @GetMapping("/remover/{id}")
    public String remover(@PathVariable Long id, ModelMap model) {
         model.addAttribute("pedido",pedidoService.findById(id).orElseThrow(() ->
                 new RuntimeException("Pedidos não encontrado")));
         return "/carrinho/remover";
    }
     
    @PostMapping("/excluir/{id}")
    public String confirmarExclusao(@PathVariable Long id, ModelMap model) {
         pedidoService.deleteById(id);
         return "redirect:/admin/carrinho/pedidos";
     }
    
}
