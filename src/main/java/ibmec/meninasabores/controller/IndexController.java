/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author gabri
 */
@Controller
@RequestMapping("ibmec-test/index")
public class IndexController {
    
    @GetMapping({"","/","principal"})
    public String index() {
        return "index/index";
    }
    
    @GetMapping("contato")
    public String contato() {
        return "index/contato";
    }
    
    @GetMapping("quemsomos")
    public String quemsomos() {
        return "index/quemsomos";
    }
    
    
    
}
