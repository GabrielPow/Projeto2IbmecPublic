/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.service;

import ibmec.meninasabores.model.Pedidos;
import ibmec.meninasabores.repository.PedidosRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gabri
 */

@Service
public class PedidosService {
    
    @Autowired
    private PedidosRepository pedidoRepository;


    public Pedidos save(Pedidos pedido) {
        return pedidoRepository.save(pedido);
    }
    
    public List<Pedidos> findAll() {
        return pedidoRepository.findAll();
    }
    
    public Optional<Pedidos> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    public void deleteById(Long id) {
        pedidoRepository.deleteById(id);
    }

    public Pedidos update(Pedidos pedido) {
        return pedidoRepository.save(pedido);
    }
    
    public long count() {
        return pedidoRepository.count();
    }
    
}
