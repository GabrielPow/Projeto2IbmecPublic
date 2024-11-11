/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.controller;

import ibmec.meninasabores.model.Carrinho;
import ibmec.meninasabores.model.Produto;
import ibmec.meninasabores.service.CarrinhoService;
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
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author gabri
 */
@Controller
@RequestMapping("/ibmec-test/carrinho")
@SessionAttributes("carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private ProdutoService produtoService;

    @ModelAttribute("carrinho")
    public Carrinho getCarrinho() {
        return new Carrinho();
    }

    @GetMapping("/carrinho")
    public String Carrinho(@ModelAttribute("carrinho") Carrinho carrinho, ModelMap model) {
        model.addAttribute("carrinho",carrinho);
        return "carrinho/cart";
    }
    
    @GetMapping("/pedidos")
    public String listar(ModelMap model) {
         List<Carrinho> carrinhos = carrinhoService.findAll();
         List<Carrinho> sortedCarrinhos = carrinhos.stream()
                 .collect(Collectors.toList());
         model.addAttribute("carrinhos", sortedCarrinhos);
         return "/carrinho/listar";
    }

    @PostMapping("/salvarProduto/{id}")
    public String addProductToCarrinho(@ModelAttribute("carrinho") Carrinho carrinho, @PathVariable UUID id) {
        Produto product = produtoService.findById(id).orElseThrow(() -> 
            new RuntimeException("Produto não encontrado"));
        carrinho.getcProdutos().add(product);
        carrinho.addPercentual(carrinho.getcProdutos().getLast().getPercentual());
        if (carrinho.getStatus() == null) {
            carrinho.setStatus("Comprando");
        }
        carrinhoService.save(carrinho);
        return "redirect:/ibmec-test/carrinho/carrinho";
    }
    
    @PostMapping("/exportarCarrinho")
    public String exportarProdutos(@ModelAttribute("carrinho") Carrinho carrinho, ModelMap model) {
        carrinho.setStatus("Em Pagamento");
        carrinhoService.save(carrinho);
        Carrinho novoCarrinho = new Carrinho();
        model.addAttribute("carrinho",novoCarrinho);
        return "redirect:/ibmec-test/carrinho/carrinho";
    }
    
    @PostMapping("/salvar")
    public String salvar(Produto produto) {
         produtoService.save(produto);
         return "redirect:/ibmec-test/produto/listar";
    }
     
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable UUID id, ModelMap model) {
         model.addAttribute("produto", produtoService.findById(id));
         return "/produto/editar";
    }
     
    @PostMapping("/atualizar")
    public String atualizar(@Valid @ModelAttribute Produto produto,
             BindingResult bindingResult, ModelMap model) {
         if (bindingResult.hasErrors()) {
             model.addAttribute("produto", produto);
             return "/produto/editar";
         }
         produtoService.update(produto);
         return "redirect:/ibmec-test/produto/listar";
     }
 
     
     @GetMapping("/remover/{id}")
     public String remover(@PathVariable UUID id, ModelMap model) {
         model.addAttribute("produto",produtoService.findById(id).orElseThrow(() ->
                 new RuntimeException("Produto não encontrado")));
         return "/produto/remover";
     }
     
     @PostMapping("/excluir/{id}")
     public String confirmarExclusao(@PathVariable UUID id, ModelMap model) {
         carrinhoService.deleteById(id);
         return "redirect:/ibmec-test/produto/listar";
     }
    
    
}
