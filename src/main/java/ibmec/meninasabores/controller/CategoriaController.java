/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.controller;

import ibmec.meninasabores.model.Categoria;
import ibmec.meninasabores.service.CategoriaService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/admin/categoria")
public class CategoriaController {
    
    
    @Autowired
    private CategoriaService categoriaService;
     
    @GetMapping("/listar")
    public String listar(ModelMap model) {
        List<Categoria> categorias = categoriaService.findAll();
        List<Categoria> sortedCategorias = categorias.stream()
                .sorted((categoria1, categoria2) -> categoria1.getNome().compareTo(categoria2.getNome()))
                .collect(Collectors.toList());
        model.addAttribute("categorias", sortedCategorias);
        return "/categoria/listar";
    }
     
     
    @GetMapping("/novo")
    public String inserir(Categoria categoria, ModelMap model) {
         return "/categoria/inserir";
    }
     
    @PostMapping("/salvar")
    public String salvar(Categoria categoria) {
         categoriaService.save(categoria);
         return "redirect:/admin/categoria/listar";
    }
     
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable UUID id, ModelMap model) {
         model.addAttribute("categoria", categoriaService.findById(id));
         return "/categoria/editar";
    }
     
    @PostMapping("/atualizar")
    public String atualizar(@Valid @ModelAttribute Categoria categoria,
             BindingResult bindingResult, ModelMap model) {
         if (bindingResult.hasErrors()) {
             model.addAttribute("categoria", categoria);
             return "/categoria/editar";
         }
         categoriaService.update(categoria);
         return "redirect:/admin/categoria/listar";
    }
 
     
    @GetMapping("/remover/{id}")
    public String remover(@PathVariable UUID id, ModelMap model) {
        model.addAttribute("categoria",categoriaService.findById(id).orElseThrow(() ->
                new RuntimeException("Categoria não encontrado")));
        return "/categoria/remover";
    }
     
    @PostMapping("/excluir/{id}")
    public String confirmarExclusao(@PathVariable UUID id, ModelMap model) {
         categoriaService.deleteById(id);
         return "redirect:/admin/categoria/listar";
    }
     
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Categoria> getProdutoByNome(@PathVariable String nome) {
        Optional<Categoria> categoria = categoriaService.findByNome(nome);
        return categoria.map(ResponseEntity::ok)
             .orElseGet(() -> ResponseEntity.notFound().build());
    }
  
}

