/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.service;

import ibmec.meninasabores.model.ImagemEntity;
import ibmec.meninasabores.repository.ImagemRepository;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author gabri
 */
@Service
public class ImagemService {
    
    
    @Autowired
    private ImagemRepository imagemRepository;

    public ImagemEntity uploadFile(MultipartFile file) throws IOException {
        ImagemEntity fileEntity = new ImagemEntity();
        fileEntity.setName(file.getOriginalFilename());
        fileEntity.setType(file.getContentType());
        fileEntity.setData(file.getBytes());
        return imagemRepository.save(fileEntity);
    }

    public ImagemEntity getFile(Long id) {
        return imagemRepository.findById(id).orElse(null);
    }
    
    public List<ImagemEntity> findAll() {
        return imagemRepository.findAll();
    }
    
}