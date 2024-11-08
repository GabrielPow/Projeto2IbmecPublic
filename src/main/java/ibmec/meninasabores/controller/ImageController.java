/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.controller;

import ibmec.meninasabores.model.ImagemEntity;
import ibmec.meninasabores.service.ImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/files")
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
        return "redirect:/files/upload-page";
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
        } else if (fileName.endsWith(".gif")) {
            return "image/gif";
        }
     // Add more types if needed
    return "application/octet-stream"; // Default type if unknown
    }
    
    
}
