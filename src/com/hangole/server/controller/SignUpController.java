package com.hangole.server.controller;

import com.hangole.server.dao.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hojak on 2017-04-04.
 */
public class SignUpController extends HttpServlet {

    private String id;
    private String password;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("euc-kr");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        System.out.println("signup.do");

        id = req.getParameter("id");
        password = req.getParameter("password");

        System.out.println("getId : "+id);
        System.out.println("getPwd : "+password);

        AccountDAO dao = AccountDAO.getInstance();
        dao.insertSignUpInfo(id, password);
    }
}

