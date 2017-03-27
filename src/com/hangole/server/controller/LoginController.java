package com.hangole.server.controller;

import com.hangole.server.dao.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dsm_025 on 2017-03-27.
 */
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("euc-ke");
        String id = req.getParameter("id");
        String password = req.getParameter("psw");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        AccountDAO dao = AccountDAO.getInstance();

        PrintWriter out = resp.getWriter();
    }
}
