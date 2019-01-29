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
public class Categoria implements Serializable {
    
    @Id
    @GeneratedValue
    private Long categoriaId;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String descricao;
    
    @OneToMany(mappedBy = "categoria", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<Item>();
    
    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long id) {
        this.categoriaId = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao()
    {
	return descricao;
    }

    public void setDescricao(String descricao)
    {
	this.descricao = descricao;
    }

    public List<Item> getItems()
    {
	return items;
    }

    public void setItems(List<Item> items)
    {
	this.items = items;
    }
    
}
