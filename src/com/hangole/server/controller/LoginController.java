package com.hangole.server.controller;

import com.hangole.server.dao.AccountDAO;
import com.hangole.server.session.User;
import com.hangole.server.session.Util;

import javax.servlet.RequestDispatcher;
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

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");
        String password = req.getParameter("password");

        AccountDAO dao = AccountDAO.getInstance();
System.out.println(id);
System.out.println(password);
        String regPsw = dao.getPasswordFromID(id);
        if (regPsw == null || !regPsw.equals(password)) {
            System.out.println("Login Failed");
            resp.sendRedirect("index.do");
        }else{
            HttpSession session = req.getSession();
            session.setAttribute("user", new User(id, password));
            System.out.println("Login Succeed");
            Util.addSession(session);
            resp.sendRedirect("game.do");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
    }
}
