/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.controller;

import ibmec.meninasabores.model.Categoria;
import ibmec.meninasabores.model.ImagemEntity;
import ibmec.meninasabores.model.Pedidos;
import ibmec.meninasabores.model.Produto;
import ibmec.meninasabores.service.CategoriaService;
import ibmec.meninasabores.service.ImagemService;
import ibmec.meninasabores.service.MensagenService;
import ibmec.meninasabores.service.NewsletterService;
import ibmec.meninasabores.service.PedidosService;
import ibmec.meninasabores.service.ProdutoService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author gabri
 */
@Controller
@RequestMapping("/admin/produto")
public class ProdutoController {
    
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private CategoriaService categoriaRepository;
    
    @Autowired
    private ImagemService imageService;
    
    @Autowired
    private PedidosService pedidoService;
    
    @Autowired
    private MensagenService mensagemService;
    
    @Autowired
    private NewsletterService newsletterService;
    
    @GetMapping("/painel")
    public String painel(ModelMap model) {
        model.addAttribute("totalProdutos", produtoService.count());
        model.addAttribute("totalCategorias", categoriaRepository.count());
        model.addAttribute("totalImagens", imageService.count());
        model.addAttribute("totalPedidos", pedidoService.count());
        model.addAttribute("totalMensagens", mensagemService.count());
        model.addAttribute("totalNewsletter", newsletterService.count());
        return "produto/paineladm";
    }
    
    @GetMapping("/listar")
     public String listar(ModelMap model) {
         List<Produto> produtos = produtoService.findAll();
         List<Produto> sortedProdutos = produtos.stream()
                 .sorted((produto1, produto2) -> produto1.getNome().compareTo(produto2.getNome()))
                 .collect(Collectors.toList());
         model.addAttribute("produtos", sortedProdutos);
         return "produto/lista_produtos";
     }
     
     
     @GetMapping("/novo")
     public String inserir(Produto produto, ModelMap model) {
         List<Categoria> categorias = categoriaRepository.findAll();
         List<ImagemEntity> imagens = imageService.findAll();
         model.addAttribute("imagens",imagens);
         model.addAttribute("categorias",categorias);
         return "produto/adicionar_produto";
     }
     
    /*@GetMapping("/listaProduto")
     public String listaProduto(ModelMap model) {
         List<Produto> produtos = produtoService.findAll();
         List<Produto> sortedProdutos = produtos.stream()
                 .filter(Produto::isStatus)
                 .filter(Produto::isDestaque)
                 .sorted((produto1, produto2) -> produto1.getNome().compareTo(produto2.getNome()))
                 .collect(Collectors.toList());
         model.addAttribute("produtosDestaque", sortedProdutos);
         List<Produto> ProdutosLicor = produtos.stream()
                 .filter(Produto::isStatus)
                 .filter(produto -> "Licor".equalsIgnoreCase(produto.getCategoria().getNome()))
                 .sorted((produto1, produto2) -> produto1.getNome().compareTo(produto2.getNome()))
                 .collect(Collectors.toList());
         model.addAttribute("produtosLicor", ProdutosLicor);
         List<Produto> ProdutosGeleia = produtos.stream()
                 .filter(Produto::isStatus)
                 .filter(produto -> "Geleia".equalsIgnoreCase(produto.getCategoria().getNome()))
                 .sorted((produto1, produto2) -> produto1.getNome().compareTo(produto2.getNome()))
                 .collect(Collectors.toList());
         model.addAttribute("produtosGeleia", ProdutosGeleia);
         return "/produto/listaProduto";
     }
     
     @GetMapping("/vizualizar/{id}")
     public String vizualizar(@PathVariable UUID id, ModelMap model) {
         model.addAttribute("produto",produtoService.findById(id).orElseThrow(() ->
                 new RuntimeException("Produto não encontrado")));
         return "/produto/productpage";
     }*/
     
     @PostMapping("/salvar")
     public String salvar(Produto produto, @RequestParam("status") boolean status) {
         if (status == true) {
             produto.setStatus("SIM");
         } else {
             produto.setStatus("NÃO");
         }
         produtoService.save(produto);
         return "redirect:/admin/produto/listar";
     }
     
     @GetMapping("/editar/{id}")
     public String editar(@PathVariable UUID id, ModelMap model) {
         model.addAttribute("produto", produtoService.findById(id));
         List<Categoria> categorias = categoriaRepository.findAll();
         model.addAttribute("categorias", categorias);
         List<ImagemEntity> imagens = imageService.findAll();
         model.addAttribute("imagens",imagens);
         return "produto/editar_produto";
     }
     
     @PostMapping("/atualizar")
     public String atualizar(@Valid @ModelAttribute Produto produto,
             BindingResult bindingResult, ModelMap model) {
         if (bindingResult.hasErrors()) {
             model.addAttribute("produto", produto);
             return "produto/editar";
         }
         produtoService.update(produto);
         return "redirect:/admin/produto/listar";
     }
 
     
     @GetMapping("/remover/{id}")
     public String remover(@PathVariable UUID id, ModelMap model) {
         model.addAttribute("produto",produtoService.findById(id).orElseThrow(() ->
                 new RuntimeException("Produto não encontrado")));
         return "produto/remover";
     }
     
     @PostMapping("/excluir/{id}")
     public String confirmarExclusao(@PathVariable UUID id, ModelMap model) {
         produtoService.deleteById(id);
         return "redirect:/admin/produto/listar";
     }
     
     @GetMapping("/nome/{nome}")
     public ResponseEntity<Produto> getProdutoByNome(@PathVariable String nome) {
         Optional<Produto> produto = produtoService.findByNome(nome);
         return produto.map(ResponseEntity::ok)
                 .orElseGet(() -> ResponseEntity.notFound().build());
     }
     
     @GetMapping("/vendidos")
     public String vendidos(ModelMap model) {
         List<Produto> produtos = produtoService.findAll();
         List<Pedidos> pedidos = pedidoService.findAll();
         Map<String, Double> contaProdutoNome = new HashMap<>();
         for (Produto produto : produtos) {
            String produtoNome = produto.getNome();
            double produtoPreco = produto.getPercentual();
            int count = 0;
            for (Pedidos pedido : pedidos) {
                List<String> nomesProdutos = pedido.getNomeProdutos();
                for (String nome : nomesProdutos) {
                    if (nome.equals(produtoNome)) {
                        count++;
                    }
                }
            }
            double total = count * produtoPreco;
            contaProdutoNome.put(produtoNome, total);
         }
         model.addAttribute("contaProdutos", contaProdutoNome);
         return "produto/lista_estatistica";
     }
}
