/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.*;
import entidades.*;
import java.util.*;
import javax.annotation.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Rafael Avilar
 */
@ManagedBean(name = "dashboardMB")
@RequestScoped
public class DashboardMB
{
    private String mensagem;
    
    private Categoria categoria;
    private List<Categoria> categorias;
    
    private Item item;
    private List<Item> items;
    
    private Note note;
    private List<Note> notes;
    
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private ItemDAO itemDAO = new ItemDAO();
    private NotesDAO notesDAO = new NotesDAO();

    public DashboardMB()
    {
	
    }
    
    public Categoria getCategoria()
    {
	return categoria;
    }
    public void setCategoria(Categoria categoria)
    {
	this.categoria = categoria;
    }
    public List<Categoria> getCategorias()
    {
	return categorias;
    }
    public void setCategorias(List<Categoria> categorias)
    {
	this.categorias = categorias;
    }
    public Item getItem()
    {
	return item;
    }
    public void setItem(Item item)
    {
	this.item = item;
    }
    public List<Item> getItems()
    {
	return items;
    }
    public void setItems(List<Item> items)
    {
	this.items = items;
    }
    public Note getNote()
    {
	return note;
    }
    public void setNote(Note note)
    {
	this.note = note;
    }
    public List<Note> getNotes()
    {
	return notes;
    }
    public void setNotes(List<Note> notes)
    {
	this.notes = notes;
    }
    public CategoriaDAO getCategoriaDAO()
    {
	return categoriaDAO;
    }
    public void setCategoriaDAO(CategoriaDAO categoriaDAO)
    {
	this.categoriaDAO = categoriaDAO;
    }
    public ItemDAO getItemDAO()
    {
	return itemDAO;
    }
    public void setItemDAO(ItemDAO itemDAO)
    {
	this.itemDAO = itemDAO;
    }
    public NotesDAO getNotesDAO()
    {
	return notesDAO;
    }
    public void setNotesDAO(NotesDAO notesDAO)
    {
	this.notesDAO = notesDAO;
    }
    public String getMensagem()
    {
	return mensagem;
    }
    public void setMensagem(String mensagem)
    {
	this.mensagem = mensagem;
    }
    
    @PostConstruct
    public void refreshPC() {
	categoria = new Categoria();
	item = new Item();
	note = new Note();
	refresh();
    }
    
    public String refresh() {
	try {
	    categorias = categoriaDAO.obterTodos();
	    items = itemDAO.obterTodos();
	    notes = notesDAO.obterTodos();
	} catch (Exception e) {
	    setMensagem("Não foi possível obter os dados do servidor");
	}
	return "refresh";
    }
    
    /// Categorias ///
    
    public String addCategoria() {
	try {
	    if (categoria.getNome().isEmpty() || 
		categoria.getNome().equals(" ") || 
		categoria.getDescricao().isEmpty() || 
		categoria.getDescricao().equals(" ")) {
		throw new StringIndexOutOfBoundsException();
	    }
	    categoriaDAO.inserir(categoria);
	} catch (StringIndexOutOfBoundsException e) {
	    setMensagem("Categorias devem possuir nome e descricao");
	} catch (Exception e) {
	    setMensagem("Erro ao criar categoria");
	}
	setMensagem("Categoria criada");
	return refresh();
    }
    
    public String delCategoria(Categoria c) {
	try {
	    categoriaDAO.remover(c);
	} catch (Exception e) {
	    setMensagem("Não foi possível remover a categoria");
	}
	setMensagem("Categoria removida");
	return refresh();
    }
    
    public List<Categoria> getAllCategorias() {
	List<Categoria> rs = new ArrayList<>();
	
	try {
	    rs = categoriaDAO.obterTodos();
	} catch (Exception e) {
	}
	return rs;
    }
    
    public String editCategoria(Categoria c) {
	try {
	    categoriaDAO.remover(c);
	} catch (Exception e) {
	    setMensagem("Não foi possível editar a categoria");
	}
	setMensagem("Categoria atualizada");
	return refresh();
    }
    
    /// Items ///
    
    public String addItem() {
	try{
	    itemDAO.inserir(item);
	} catch (Exception e) {
	    setMensagem("Não foi possível adicionar o item");
	}
	setMensagem("Item criado");
	return refresh();
    }
    
    public String delItem(Item i) {
	try {
	    itemDAO.remover(i);
	} catch (Exception e) {
	    setMensagem("Não foi possível remover o item");
	}
	setMensagem("Item removido");
	return refresh();
    }
    
    public List<Item> getItemsFrom(Categoria c) {
	List<Item> rs = new ArrayList<>();
	
	try {
	    rs = itemDAO.obterTodos(c.getCategoriaId());
	} catch (Exception e) {
	}
	return rs;
    }
    
    public String addItemPara(Long cId) {
	try{
	    Categoria cat = categoriaDAO.obter(cId);
	    item.setCategoria(cat);
	    itemDAO.inserir(item);
	} catch (Exception e) {
	    setMensagem("Não foi possível adicionar o item");
	}
	setMensagem("Item criado");
	return refresh();
    }
    
    public String editItem(Long id) {
	try {
	    Item i = itemDAO.obter(id);
	    i.setNome(item.getNome());
	    i.setAutor(item.getAutor());
	    i.setStatus(item.getStatus());
	    itemDAO.atualizar(i);
	} catch (Exception e) {
	    setMensagem("Não foi possível atualizar o item");
	}
	setMensagem("Item atualizado");
	return refresh();
    }
    
    /// Notes ///
    public String addNotePara(Long iId) {
	try{
	    Item item = itemDAO.obter(iId);
	    note.setItem(item);
	    notesDAO.inserir(note);
	} catch (Exception e) {
	    setMensagem("Não foi possível adicionar a anotação");
	}
	setMensagem("Anotação criada");
	return refresh();
    }
    
    public String delNote(Note n) {
	try {
	    notesDAO.remover(n);
	} catch (Exception e) {
	    setMensagem("Não foi possível remover a anotação");
	}
	setMensagem("Anotação removida");
	return refresh();
    }
    
    public List<Note> getNotesFrom(Item i) {
	List<Note> rs = new ArrayList<>();
	
	try {
	    rs = notesDAO.obterTodos(i.getItemId());
	} catch (Exception e) {
	}
	return rs;
    }

}
