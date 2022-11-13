package com.springboot.insurtechbackend.CommonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@WebServlet("/Utilities")

/*
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.

*/

public class Utilities extends HttpServlet {
    HttpServletRequest req;
    PrintWriter pw;
    String url;
    HttpSession session;
    public Utilities(HttpServletRequest req, PrintWriter pw) {
        this.req = req;
        this.pw = pw;
        this.url = this.getFullURL();
        this.session = req.getSession(true);
    }



    /*  getFullURL Function - Reconstructs the URL user request   */

    public String getFullURL() {
        String scheme = req.getScheme();
        String serverName = req.getServerName();
        int serverPort = req.getServerPort();
        String contextPath = req.getContextPath();
        StringBuffer url = new StringBuffer();
        url.append(scheme).append("://").append(serverName);

        if ((serverPort != 80) && (serverPort != 443)) {
            url.append(":").append(serverPort);
        }
        url.append(contextPath);
        url.append("/");
        return url.toString();
    }

    /*  logout Function removes the username , usertype attributes from the session variable*/

    public void logout(){
        session.removeAttribute("UserName");
        session.removeAttribute("Password");
        session.removeAttribute("UserType");

    }

    /*  logout Function checks whether the user is loggedIn or Not*/

    public boolean isLoggedin(){
        if (session.getAttribute("username")==null)
            return false;
        return true;
    }

    /*  username Function returns the username from the session variable.*/

    public String username(){
        if (session.getAttribute("username")!=null)
            return session.getAttribute("username").toString();
        return null;
    }

    /*  usertype Function returns the usertype from the session variable.*/
    public String usertype(){
        if (session.getAttribute("usertype")!=null)
            return session.getAttribute("usertype").toString();
        return null;
    }




}
