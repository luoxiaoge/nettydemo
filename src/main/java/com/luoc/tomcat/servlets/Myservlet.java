package com.luoc.tomcat.servlets;

import com.luoc.tomcat.http.GPRequest;
import com.luoc.tomcat.http.GPResponse;
import com.luoc.tomcat.http.GPServlet;
import java.io.UnsupportedEncodingException;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2018/9/14 9:46
 */
public class Myservlet extends GPServlet {

    @Override
    public void doGet(GPRequest request, GPResponse response) {
        try {
            response.write(request.getParameter("name"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(GPRequest request, GPResponse response) {
        super.doPost(request, response);
    }
}
