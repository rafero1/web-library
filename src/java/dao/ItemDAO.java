/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.*;
import java.util.*;
import org.hibernate.*;

/**
 *
 * @author Rafael Avilar
 */
public class ItemDAO extends DAO<Item> {
    
    public ItemDAO() {
	super(Item.class);
    }
    
    public List<Item> obterTodos(Long categoriaId) {
	List<Item> rs = new ArrayList<>();
	Session session = util.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            rs = (List<Item>) session.createQuery("FROM " +Item.class.getName()+ " WHERE categoria_categoriaid = "+categoriaId).list();
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
