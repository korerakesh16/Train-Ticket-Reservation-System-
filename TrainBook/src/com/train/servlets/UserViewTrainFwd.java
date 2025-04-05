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
@WebServlet("/userviewtrainfwd")
public class UserViewTrainFwd extends HttpServlet {

    TrainService trainService = new TrainServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);

        try {
            List<TrainBean> trains = trainService.getAllTrains();
            RequestDispatcher rd = req.getRequestDispatcher("UserViewTrains.html");
            rd.include(req, res);

            pw.println("<div class='container mt-4'>");
            pw.println("<div class='alert alert-primary text-center' role='alert'>");
            pw.println("<h4>Available Trains</h4>");
            pw.println("</div>");

            pw.println("<table class='table table-bordered table-hover text-center' style='background-color: #f8f9fa; border-radius: 10px;'>");
            pw.println("<thead class='thead-dark'>"
                    + "<tr>"
                    + "<th>Train Name</th><th>Train No</th><th>From</th><th>To</th>"
                    + "<th>Departure Time</th><th>Seats</th><th>Fare (INR)</th><th>Action</th>"
                    + "</tr></thead><tbody>");

            if (trains != null && !trains.isEmpty()) {
                for (TrainBean train : trains) {
                    int hr = (int) (Math.random() * 24);
                    int min = (int) (Math.random() * 60);
                    String time = String.format("%02d:%02d", hr, min);

                    pw.println("<tr style='font-size: 18px; font-weight: bold; color: #343a40;'>"
                            + "<td>" + train.getTr_name() + "</td>"
                            + "<td>" + train.getTr_no() + "</td>"
                            + "<td>" + train.getFrom_stn() + "</td>"
                            + "<td>" + train.getTo_stn() + "</td>"
                            + "<td><b>" + time + "</b></td>"
                            + "<td class='text-success'><b>" + train.getSeats() + "</b></td>"
                            + "<td><b>" + train.getFare() + "</b></td>"
                            + "<td><a href='booktrainbyref?trainNo=" + train.getTr_no() + "&fromStn=" + train.getFrom_stn() 
                            + "&toStn=" + train.getTo_stn() 
                            + "' class='btn btn-success btn-sm'>Book Now</a></td></tr>");
                }
            } else {
                pw.println("<tr><td colspan='8' class='text-danger'><b>No Running Trains Available</b></td></tr>");
            }

            pw.println("</tbody></table>");
            pw.println("</div>");
        } catch (Exception e) {
            throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
        }
    }
}
