package com.train.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.train.constant.UserRole;
import com.train.utility.TrainUtil;

@SuppressWarnings("serial")
@WebServlet("/userprofile")
public class UserProfile extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);

        RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
        rd.include(req, res);

        pw.println("<div class='container' style='width: 80%; margin: 0 auto; background-color: white; padding: 20px; border-radius: 10px;'>");

        pw.println("<div class='tab' style='font-size: 20px; font-weight: bold; color: black;'>"
                + "Hello " + TrainUtil.getCurrentUserName(req) + " ! Welcome to our new NITRTC Website"
                + "</div>");

        pw.println("<div class='main' style='margin-top: 20px;'>"
                + "<p1 class='menu' style='font-size: 18px; color: black;'>"
                + "<a href='viewuserprofile' style='color: white; background-color: #007BFF; padding: 10px 20px; text-decoration: none; border-radius: 5px; margin-right: 10px;'>View Profile</a>"
                + "<a href='edituserprofile' style='color: white; background-color: #28A745; padding: 10px 20px; text-decoration: none; border-radius: 5px; margin-right: 10px;'>Edit Profile</a>"
                + "<a href='changeuserpassword' style='color: white; background-color: #FFC107; padding: 10px 20px; text-decoration: none; border-radius: 5px;'>Change Password</a>"
                + "</p1>"
                + "</div>");

        pw.println("<div class='tab yellow' style='font-size: 18px; color: black;'>"
                + "Hey! " + TrainUtil.getCurrentUserName(req)
                + ", Welcome to NITRTC<br/><br/>"
                + "Here you can Edit, View Your Profile, and Change Your Password.<br/><br/>"
                + "Thanks for being connected with us!"
                + "</div>");

        pw.println("</div>");
    }
}
