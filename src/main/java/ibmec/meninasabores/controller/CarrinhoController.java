/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.controller;

import ibmec.meninasabores.model.Carrinho;
import ibmec.meninasabores.model.Cliente;
import ibmec.meninasabores.model.Produto;
import ibmec.meninasabores.service.CarrinhoService;
import ibmec.meninasabores.service.ClienteService;
import ibmec.meninasabores.service.ProdutoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
    
    @Autowired
    private ClienteService clienteService;
    
    @ModelAttribute("carrinho")
    public Carrinho getCarrinho() {
        return new Carrinho();
    }

    @GetMapping("/carrinho")
    public String Carrinho(@ModelAttribute("carrinho") Carrinho carrinho, ModelMap model) {
        carrinho.getcProdutos().sort(Comparator.comparing(produto -> produto.getNome()));
        model.addAttribute("carrinho",carrinho);
        return "carrinho/cart";
    }
    
    @GetMapping("/pedidos")
    public String listar(ModelMap model) {
         List<Carrinho> carrinhos = carrinhoService.findAll();
         List<Carrinho> sortedCarrinhos = carrinhos.stream()
                 .filter(carrinho -> !"Comprando".equals(carrinho.getStatus()))
                 .collect(Collectors.toList());
         model.addAttribute("carrinhos", sortedCarrinhos);
         return "/carrinho/listar";
    }

    @PostMapping("/salvarProduto/{id}")
    public String addProductToCarrinho(@ModelAttribute("carrinho") Carrinho carrinho, @PathVariable UUID id) {
        Produto product = produtoService.findById(id).orElseThrow(() -> 
            new RuntimeException("Produto não encontrado"));
        carrinho.getcProdutos().add(product);
        double temp = produtoService.findById(id).get().getPercentual();
        carrinho.addPercentual(temp);
        if (carrinho.getStatus() == null) {
            carrinho.setStatus("Comprando");
        }
        if (carrinho.getCliente() == null) {
            Optional<Cliente> test = clienteService.findById(1L);
            Cliente cliente;
            if (test.isPresent()) {
                cliente = test.get();
            } else {
                cliente = new Cliente();
                cliente.setNomec("NA");
                cliente.setEmail("EMAIL@EMAIL.COM");
                cliente.setIdCliente(1L);
                clienteService.save(cliente);
            }
            carrinho.setCliente(cliente);
    }
        carrinhoService.save(carrinho);
        return "redirect:/ibmec-test/carrinho/carrinho";
    }
    
    @PostMapping("/exportarCarrinho")
    public String exportarProdutos(@ModelAttribute("carrinho") Carrinho carrinho, ModelMap model,@RequestParam String name,@RequestParam String email) {
        Optional<Cliente> insert = clienteService.findByNomec(name);
        if (!insert.isPresent()) {
            Cliente novoCliente = new Cliente(name, email);
            clienteService.save(novoCliente);
        }
        Cliente cliente = clienteService.findByNomec(name).orElseThrow(() ->
                new RuntimeException("Cliente Não encontrado"));
        carrinho.setCliente(cliente);
        carrinho.setStatus("Em Pagamento");
        carrinhoService.save(carrinho);
        Carrinho novoCarrinho = new Carrinho();
        model.addAttribute("carrinho",novoCarrinho);
        return "redirect:/ibmec-test/carrinho/carrinho";
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
         return "redirect:/ibmec-test/carrinho/pedidos";
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
         return "redirect:/ibmec-test/carrinho/pedidos";
     }
     
    @GetMapping("/removerProduto/{id}")
     public String removerProduto(@PathVariable UUID id, @ModelAttribute("carrinho") Carrinho carrinho, ModelMap model) {
         for (Produto test : carrinho.getcProdutos()) {
             if (test.getIdProduto().equals(id)) {
                 model.addAttribute("produto",test);
             }
         }
         return "/carrinho/removerProduto";
    }


     @PostMapping("/excluirProduto/{id}")
     public String removeProductToCarrinho(@ModelAttribute("carrinho") Carrinho carrinho, @PathVariable UUID id, HttpSession session) {
        Produto product = null;
         for (Produto produto : carrinho.getcProdutos()) {
            if (produto.getIdProduto().equals(id)) {
                product = produto;
            }
        }
        double temp = produtoService.findById(id).get().getPercentual();
        carrinho.removeProduto(product);
        carrinho.subPercentual(temp);
        carrinhoService.update(carrinho);
        return "redirect:/ibmec-test/carrinho/carrinho";
    }

    
}
