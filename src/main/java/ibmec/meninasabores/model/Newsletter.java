/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibmec.meninasabores.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

/**
 *
 * @author gabri
 */

@Entity
@Table(name = "TB_NEWSLETTER", schema = "MenSab")
public class Newsletter {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_NEWSLETTER")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idNewsletter;
    @Column(name = "EMAIL", nullable = false)
    private String email;
    
    public Newsletter() {
    
    }

    public Newsletter(String email) {
        this.email = email;
    }

    public UUID getIdNewsletter() {
        return idNewsletter;
    }

    public void setIdNewsletter(UUID idNewsletter) {
        this.idNewsletter = idNewsletter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
}
