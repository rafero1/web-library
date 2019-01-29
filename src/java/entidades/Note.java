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
 * @author Rafael Avilar
 */
@Entity
public class Note implements Serializable {
    
    @Id
    @GeneratedValue
    private Long noteId;
    
    @Column(nullable = false)
    private String content;
    
    @ManyToOne()
    private Item item;

    public Long getNoteId()
    {
	return noteId;
    }

    public void setNoteId(Long noteId)
    {
	this.noteId = noteId;
    }

    public String getContent()
    {
	return content;
    }

    public void setContent(String content)
    {
	this.content = content;
    }

    public Item getItem()
    {
	return item;
    }

    public void setItem(Item item)
    {
	this.item = item;
    }

}
