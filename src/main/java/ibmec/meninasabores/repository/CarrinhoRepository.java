/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.repository;

import ibmec.meninasabores.model.Carrinho;
import ibmec.meninasabores.model.Produto;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gabri
 */
public interface CarrinhoRepository extends JpaRepository<Carrinho, UUID>{
    Optional<Produto> findByNome(String nome);
}
