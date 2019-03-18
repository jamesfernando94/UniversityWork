package com.jamesfernando.jsfvisitorbook.ejb;

import com.jamesfernando.jsfvisitorbook.entity.Comment;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CommentStorageServiceBean implements CommentStorageService{

    @PersistenceContext
    EntityManager em;
    
    
    public CommentStorageServiceBean() {
    }

    @Override
    public List<Comment> getCommentList() {
        return em.createNamedQuery("findAllComments").getResultList();
    }
    
    @Override
    public void insertComment(String name, String comment_str, Date visitDate) {
        Comment cmnt = new Comment(name, comment_str, visitDate);
        
        em.persist(cmnt);
        System.out.println("Inserted the following comment in the store:");
        System.out.println("name: " + name);
        System.out.println("date: " + visitDate);
        System.out.println("comment: " + comment_str);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("CommentStore: PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("CommentStore: PreDestroy");
    }
}
