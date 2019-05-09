package com.jamesfernando.jsfvisitorbook.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

@NamedQuery(name="findAllComments", query = "SELECT C FROM Comment c")
@Entity
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long commentID;
    @NotNull
    String name;
    @NotNull
    String comment_str;
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    Date visitDate;

    public Comment(String name, String comment_str, Date visitDate) {
        this.name = name;
        this.comment_str = comment_str;
        this.visitDate = visitDate;
    }

    public Comment(){
        
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment_str() {
        return comment_str;
    }

    public void setComment_str(String comment_str) {
        this.comment_str = comment_str;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.commentID ^ (this.commentID >>> 32));
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.comment_str);
        hash = 71 * hash + Objects.hashCode(this.visitDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comment other = (Comment) obj;
        if (this.commentID != other.commentID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.comment_str, other.comment_str)) {
            return false;
        }
        if (!Objects.equals(this.visitDate, other.visitDate)) {
            return false;
        }
        return true;
    }

}
