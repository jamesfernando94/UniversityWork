/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.serverletcommentstore;

import java.util.Date;

/**
 *
 * @author James
 */
public class Comment {

    String name;
    String comment_str;
    Date visitDate;

    public Comment(String name, String comment_str, Date visitDate) {
        this.name = name;
        this.comment_str = comment_str;
        this.visitDate = visitDate;
    }
    
}
