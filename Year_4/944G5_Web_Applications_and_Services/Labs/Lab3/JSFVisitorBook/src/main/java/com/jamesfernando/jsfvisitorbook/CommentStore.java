package com.jamesfernando.jsfvisitorbook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;

@Singleton
public class CommentStore {

    ArrayList<Comment> commentList;

    public CommentStore() {
        commentList = new ArrayList<>();
    }

    public synchronized List<Comment> getCommentList() {
        return commentList;
    }

    public synchronized void insertComment(String name, String comment_str, Date visitDate) {
        Comment cmnt = new Comment(name, comment_str, visitDate);
        commentList.add(cmnt);
        System.out.println(        "Inserted the following comment in the store:");
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
