/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.repository;

import ibmec.meninasabores.model.Produto;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gabri
 */

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    Optional<Produto> findByNome(String name);
    
}
