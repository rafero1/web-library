/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Note;
import entidades.*;
import java.util.*;
import org.hibernate.*;

/**
 *
 * @author Rafael Avilar
 */
public class NotesDAO extends DAO<Note> {
    
    public NotesDAO() {
	super(Note.class);
    }
    
    public List<Note> obterTodos(Long itemId) {
	List<Note> rs = new ArrayList<>();
	Session session = util.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            rs = (List<Note>) session.createQuery("FROM " +Note.class.getName()+ " WHERE item_itemid = "+itemId).list();
	    /*
	    for (Categoria r : rs) {
		
	    }*/
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }
	return rs;
    }
}
