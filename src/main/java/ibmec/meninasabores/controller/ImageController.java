/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.controller;

import ibmec.meninasabores.model.Categoria;
import ibmec.meninasabores.model.ImagemEntity;
import ibmec.meninasabores.service.ImagemService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author gabri
 */
@Controller
@RequestMapping("/admin/files")
public class ImageController {
    
    @Autowired
    private ImagemService ImageService;

    // Método para exibir a página de upload
    @GetMapping("/upload-page")
    public String showUploadPage() {
        return "files/upload"; // Nome do ficheiro HTML sem a extensão (.html)
    }

    // Método para fazer o upload do arquivo
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            ImageService.uploadFile(file);
            redirectAttributes.addFlashAttribute("message", "Upload realizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Falha no upload do arquivo.");
        }
        return "redirect:/admin/files/upload-page";
    }
    
    @GetMapping("/listar")
    public String listar(ModelMap model) {
        List<ImagemEntity> imagens = ImageService.findAll();
        List<ImagemEntity> sortedImagens = imagens.stream()
                .sorted((imagen1, imagen2) -> imagen1.getName().compareTo(imagen2.getName()))
                .collect(Collectors.toList());
        model.addAttribute("imagens", sortedImagens);
        return "/files/lista_imagens";
    }

    // Método para download do arquivo
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        ImagemEntity ImagemEntity = ImageService.getFile(id);
        if (ImagemEntity != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + ImagemEntity.getName() + "\"")
                    .body(ImagemEntity.getData());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    @GetMapping("/ver/{id}")
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
    
    @GetMapping("/remover/{id}")
    public String remover(@PathVariable Long id, ModelMap model) {
        model.addAttribute("imagem",ImageService.findById(id).orElseThrow(() ->
            new RuntimeException("Imagem não encontrado")));
        return "/files/remover";
     }
     
    @PostMapping("/excluir/{id}")
    public String confirmarExclusao(@PathVariable Long id, ModelMap model) {
        ImageService.deleteById(id);
        return "redirect:/admin/files/listar";
     }
 
}
