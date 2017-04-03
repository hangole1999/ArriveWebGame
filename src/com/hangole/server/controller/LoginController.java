package com.hangole.server.controller;

import com.hangole.server.session.User;
import com.hangole.server.session.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by dsm_025 on 2017-03-28.
 */
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("login.do");

        req.setCharacterEncoding("euc-kr");
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

//        AccountDAO dao = AccountDAO.getInstance();


        String regPsw = password;//dao.getPasswordFromID(id);
        if (regPsw == null || !regPsw.equals(password)) {
            System.out.println("Login Failed");
        }else{
            HttpSession session = req.getSession();
            session.setAttribute("user", new User(id, password, req.getRemoteAddr()));
            System.out.println("Login Succeed");
            Util.addSession(session);
        }
        resp.sendRedirect("index.jsp");
    }
}
