/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Classe que representa a entidade usuário
 */
@Entity
public class Item implements Serializable {
    
    @Id
    @GeneratedValue
    private Long itemId;
    
    @Column(nullable = false)
    private String nome;
    
    @Column()
    private String autor;
    
    @Column()
    private String status;
    
    @ManyToOne()
    private Categoria categoria;
    
    @OneToMany(mappedBy = "item", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Note> notes = new ArrayList<Note>();
    
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long id) {
        this.itemId = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor()
    {
	return autor;
    }

    public void setAutor(String autor)
    {
	this.autor = autor;
    }

    public String getStatus()
    {
	return status;
    }

    public void setStatus(String status)
    {
	this.status = status;
    }

    public Categoria getCategoria()
    {
	return categoria;
    }

    public void setCategoria(Categoria categoria)
    {
	this.categoria = categoria;
    }

    public List<Note> getNotes()
    {
	return notes;
    }

    public void setNotes(List<Note> notes)
    {
	this.notes = notes;
    }
    
}
