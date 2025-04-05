package com.train.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet("/trainbwstn")
public class TrainBwStn extends HttpServlet {
    private TrainService trainService = new TrainServiceImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);

        try {
            String fromStation = req.getParameter("fromstation");
            String toStation = req.getParameter("tostation");
            List<TrainBean> trains = trainService.getTrainsBetweenStations(fromStation, toStation);

            RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
            rd.include(req, res);

            pw.println("<div class='container mt-4'>");

            if (trains != null && !trains.isEmpty()) {
                pw.println("<div class='alert alert-primary text-center' role='alert'>");
                pw.println("<h4>Trains Between " + fromStation + " and " + toStation + "</h4>");
                pw.println("</div>");

                pw.println("<table class='table table-striped table-hover text-center'>");
                pw.println("<thead class='thead-dark'>"
                        + "<tr>"
                        + "<th>Train Name</th><th>Train No</th><th>From</th><th>To</th>"
                        + "<th>Departure Time</th><th>Seats</th><th>Fare (INR)</th><th>Book</th>"
                        + "</tr></thead><tbody>");

                for (TrainBean train : trains) {
                    int hr = (int) (Math.random() * 24);
                    int min = (int) (Math.random() * 60);
                    String time = String.format("%02d:%02d", hr, min);

                    pw.println("<tr>"
                            + "<td>" + train.getTr_name() + "</td>"
                            + "<td>" + train.getTr_no() + "</td>"
                            + "<td>" + train.getFrom_stn() + "</td>"
                            + "<td>" + train.getTo_stn() + "</td>"
                            + "<td>" + time + "</td>"
                            + "<td class='text-success'><b>" + train.getSeats() + "</b></td>"
                            + "<td><b>" + train.getFare() + "</b></td>"
                            + "<td><a href='booktrainbyref?trainNo=" + train.getTr_no() + "&fromStn=" 
                            + train.getFrom_stn() + "&toStn=" + train.getTo_stn() 
                            + "' class='btn btn-sm btn-success'>Book Now</a></td></tr>");
                }

                pw.println("</tbody></table>");
            } else {
                pw.println("<div class='alert alert-danger text-center' role='alert'>");
                pw.println("<h4>No trains available between " + fromStation + " and " + toStation + ".</h4>");
                pw.println("</div>");
            }

            pw.println("</div>"); // Close container
        } catch (Exception e) {
            throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
        }
    }
}
