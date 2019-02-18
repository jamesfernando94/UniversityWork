/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.serverletcommentstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author James
 */
@WebServlet("/commentstore")
public class CommentStoreServerlet extends HttpServlet {

    @EJB
    CommentStore store;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Received a GET HTTP Request");
        // read all headers - will set respective HTTP header
        Enumeration<String> headers = req.getHeaderNames();
        while (headers.hasMoreElements()) {
            String header = headers.nextElement();
            System.out.println(header + ": " + req.getHeader(header));
        }
        PrintWriter pw;
        try {
            String htmlToWrite = "<?xml version='1.0' encoding='UTF-8' ?>\n"
                    + "<!DOCTYPE html>\n"
                    + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                    + "<body>\n"
                    + "<form method=\"post\" action=\" " + req.getRequestURI() + "\" enctype=\"application/x-www-form-urlencoded\">\n"
                    + " Please enter your name:\n"
                    + " <input type=\"text\" name=\"name\" title=\"Please enter your name\" />\n"
                    + " <br>\n"
                    + " Please enter the date of your visit:\n"
                    + " <input type=\"text\" name=\"date\" title=\"Please enter the date of your visit\" />\n"
                    + " <br>\n"
                    + " Please enter your comment:\n"
                    + " <textarea name=\"comment\" title=\"Enter your comment\"></textarea>\n"
                    + " <br>\n"
                    + " <input type=\"submit\" value=\"Submit Comment\" title=\"Submit your comment\" />\n"
                    + " <input type=\"reset\" value=\"Reset Form\" title=\"Reset Form\" />\n"
                    + "</form>\n"
                    + "</body>\n"
                    + "</html>";
            // set response's content type to html MIME
            resp.setContentType("text/html");
            // set response's content length - will set respective HTTP header
            resp.setContentLength(htmlToWrite.length());
            // get a PrintWriter from the HttpServletResponse object
            pw = resp.getWriter();
            // Write the html to the PrintWriter object
            pw.print(htmlToWrite);
        } catch (IOException ex) {
            Logger.getLogger(CommentStoreServerlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String dateStr = req.getParameter("date");
        String commentStr = req.getParameter("comment");

        Date date = new Date(dateStr);

        store.insertComment(name, commentStr, date);

        List<Comment> comments = store.getCommentList();

        StringBuilder htmlToWrite = new StringBuilder();
        htmlToWrite.append("<?xml version='1.0' encoding='UTF-8' ?>\n");
        htmlToWrite.append("<!DOCTYPE html>\n");
        htmlToWrite.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
        htmlToWrite.append("<body>\n");
        htmlToWrite.append("<table>\n");
        htmlToWrite.append("<tr>\n");
        htmlToWrite.append("<th>Name</th>\n");
        htmlToWrite.append("<th>Date</th>\n");
        htmlToWrite.append("<th>Comment</th>\n");
        htmlToWrite.append("</tr>\n");

        Iterator<Comment> iter = comments.iterator();
        while (iter.hasNext()) {
            Comment comment = iter.next();
            htmlToWrite.append("<tr>\n");
            htmlToWrite.append("<td>" + comment.name + "</td>\n");
            htmlToWrite.append("<td>" + comment.visitDate.toString() + "</td>\n");
            htmlToWrite.append("<td>" + comment.comment_str + "</td>\n");
            htmlToWrite.append("</tr>\n");

        }

        htmlToWrite.append("</table>\n");

        htmlToWrite.append("</body>\n");
        htmlToWrite.append("</html>");
        // set response's content type to html MIME
        resp.setContentType("text/html");
        // set response's content length - will set respective HTTP header
        resp.setContentLength(htmlToWrite.length());
        // get a PrintWriter from the HttpServletResponse object
        PrintWriter pw = resp.getWriter();
        // Write the html to the PrintWriter object
        pw.print(htmlToWrite);
    }

}
