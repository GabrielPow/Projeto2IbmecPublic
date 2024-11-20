/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.controller;

import ibmec.meninasabores.model.Carrinho;
import ibmec.meninasabores.model.ImagemEntity;
import ibmec.meninasabores.model.Produto;
import ibmec.meninasabores.service.ImagemService;
import ibmec.meninasabores.service.ProdutoService;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author gabri
 */
@Controller
@RequestMapping("/home")
public class IndexController {
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private ImagemService ImageService;
    
    @GetMapping({"","/","principal"})
    public String index(ModelMap model) {
        List<Produto> produtos = produtoService.findAll();
        List<Produto> sortedProdutos = produtos.stream()
                .filter(Produto::isStatus)
                .filter(Produto::isDestaque)
                .sorted((produto1, produto2) -> produto1.getNome().compareTo(produto2.getNome()))
                .collect(Collectors.toList());
        model.addAttribute("produtosTodos", sortedProdutos);
        return "/index/index";
    }
    
    @GetMapping("contato")
    public String contato() {
        return "/index/contato_1";
    }
    
    @GetMapping("quemsomos")
    public String quemsomos() {
        return "/index/quemsomos";
    }
    
    @GetMapping("/loogut")
    public String logout() {
        return "/index/index"; // templates/public/index.html
    }

    @GetMapping("/login")
    public String loginPage() {
        return "/index/login"; // templates/login.html
    }
    
    @GetMapping("/buscar_produtos")
    public String buscarProduto(ModelMap model, @RequestParam(value = "query", required = false) String query) {
        List<Produto> produtos = produtoService.findAll();
        List<Produto> filteredProdutos = produtos.stream()
                .filter(Produto::isStatus)
                .filter(produto -> query.equalsIgnoreCase(produto.getCategoria().getNome()))
                .sorted((produto1, produto2) -> produto1.getNome().compareTo(produto2.getNome()))
                .collect(Collectors.toList());
        model.addAttribute("produtosQuery", filteredProdutos);
        model.addAttribute("query", query);
        return "/produto/produto";
    }
    
    @GetMapping("/produtos")
    public String listaProduto(ModelMap model) {
        List<Produto> produtos = produtoService.findAll();
        List<Produto> sortedProdutos = produtos.stream()
                .filter(Produto::isStatus)
                .sorted((produto1, produto2) -> produto1.getNome().compareTo(produto2.getNome()))
                .collect(Collectors.toList());
        model.addAttribute("produtosTodos", sortedProdutos);
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
        List<Produto> ProdutosDoce = produtos.stream()
                .filter(Produto::isStatus)
                .filter(produto -> "Doce".equalsIgnoreCase(produto.getCategoria().getNome()))
                .sorted((produto1, produto2) -> produto1.getNome().compareTo(produto2.getNome()))
                .collect(Collectors.toList());
        model.addAttribute("produtosDoce", ProdutosDoce);
        return "/produto/produto";
    }
    
    @GetMapping("/produtos/geleias")
    public String produtoGeleia(ModelMap model) {
        List<Produto> produtos = produtoService.findAll();
        List<Produto> ProdutosGeleia = produtos.stream()
                .filter(Produto::isStatus)
                .filter(produto -> "Geleia".equalsIgnoreCase(produto.getCategoria().getNome()))
                .sorted((produto1, produto2) -> produto1.getNome().compareTo(produto2.getNome()))
                .collect(Collectors.toList());
        model.addAttribute("produtosGeleia", ProdutosGeleia);
        return "/produto/produto";
    }
    
    @GetMapping("/produtos/licores")
    public String produtoLicor(ModelMap model) {
        List<Produto> produtos = produtoService.findAll();
        List<Produto> ProdutosLicor = produtos.stream()
                .filter(Produto::isStatus)
                .filter(produto -> "Licor".equalsIgnoreCase(produto.getCategoria().getNome()))
                .sorted((produto1, produto2) -> produto1.getNome().compareTo(produto2.getNome()))
                .collect(Collectors.toList());
        model.addAttribute("produtosLicor", ProdutosLicor);
        return "/produto/produto";
    }
    
    @GetMapping("/produtos/doces")
    public String produtoDoce(ModelMap model) {
        List<Produto> produtos = produtoService.findAll();
        List<Produto> ProdutosDoce = produtos.stream()
                .filter(Produto::isStatus)
                .filter(produto -> "Doce".equalsIgnoreCase(produto.getCategoria().getNome()))
                .sorted((produto1, produto2) -> produto1.getNome().compareTo(produto2.getNome()))
                .collect(Collectors.toList());
        model.addAttribute("produtosDoce", ProdutosDoce);
        return "/produto/produto";
    }
    
    @GetMapping("/vizualizar/{id}")
    public String vizualizar(@PathVariable UUID id, ModelMap model) {
         model.addAttribute("produto",produtoService.findById(id).orElseThrow(() ->
                 new RuntimeException("Produto não encontrado")));
         return "/produto/productpage";
    }
    
    @GetMapping("files/ver/{id}")
    public ResponseEntity<byte[]> displayFile(@PathVariable Long id) {
        ImagemEntity ImagemEntity = ImageService.getFile(id);

        if (ImagemEntity != null) {
            String mimeType = determineMimeType(ImagemEntity.getName());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, mimeType)
                    .body(ImagemEntity.getData());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    private String determineMimeType(String fileName) {
        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (fileName.endsWith(".png")) {
            return "image/png";
        }
        return "application/octet-stream";
    }
    
    
    
}
