package com.train.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.train.beans.TrainBean;
import com.train.beans.TrainException;
import com.train.constant.UserRole;
import com.train.service.TrainService;
import com.train.service.impl.TrainServiceImpl;
import com.train.utility.TrainUtil;

@SuppressWarnings("serial")
@WebServlet("/useravail")
public class UserAvailServlet extends HttpServlet {
    private TrainService trainService = new TrainServiceImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);

        try {
            String trainNo = req.getParameter("trainno");
            TrainBean train = trainService.getTrainById(trainNo);
            
            RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
            rd.include(req, res);

            pw.println("<div class='container mt-4'>");

            if (train != null) {
                pw.println("<div class='alert alert-success' role='alert'>");
                pw.println("<h4 class='alert-heading'>Hello " + TrainUtil.getCurrentUserName(req) + "!</h4>");
                pw.println("<p>Welcome to the Train Booking System.</p>");
                pw.println("</div>");

                pw.println("<div class='card p-3 shadow-lg' style='max-width: 600px; margin: auto;'>");
                pw.println("<h3 class='text-center text-primary'>Train Details</h3>");
                pw.println("<table class='table table-striped'>");
                pw.println("<tr><td><b>Train Name:</b></td><td>" + train.getTr_name() + "</td></tr>");
                pw.println("<tr><td><b>Train Number:</b></td><td>" + train.getTr_no() + "</td></tr>");
                pw.println("<tr><td><b>From Station:</b></td><td>" + train.getFrom_stn() + "</td></tr>");
                pw.println("<tr><td><b>To Station:</b></td><td>" + train.getTo_stn() + "</td></tr>");
                pw.println("<tr><td><b>Available Seats:</b></td><td class='text-success'><b>" + train.getSeats() + " Seats</b></td></tr>");
                pw.println("<tr><td><b>Fare (INR):</b></td><td>" + train.getFare() + " ₹</td></tr>");
                pw.println("</table>");
                pw.println("</div>");
            } else {
                pw.println("<div class='alert alert-danger text-center' role='alert'>");
                pw.println("<h4>Train No. " + trainNo + " is Not Available!</h4>");
                pw.println("</div>");
            }
            
            pw.println("</div>");
            
        } catch (Exception e) {
            throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
        }
    }
}
