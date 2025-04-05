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
@WebServlet("/fareenq")
public class FareEnq extends HttpServlet {
    TrainService trainService = new TrainServiceImpl();

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

            pw.println("<div class='white-box'>");
            pw.println("<p1 class='menu'>Fare for Trains Between Station " + fromStation + " and " + toStation + " is as below</p1>");
            pw.println("<table class='train-table'>");
            pw.println("<tr><th>Train Name</th><th>Train No</th><th>From Stn</th><th>To Stn</th>"
                    + "<th>Time</th><th>Seats</th><th>Fare (INR)</th><th>Action</th></tr>");

            if (trains != null && !trains.isEmpty()) {
                for (TrainBean train : trains) {
                    int hr = (int) (Math.random() * 24);
                    int min = (int) (Math.random() * 60);
                    String time = (hr < 10 ? ("0" + hr) : hr) + ":" + ((min < 10) ? "0" + min : min);

                    pw.println("<tr><td>" + train.getTr_name() + "</td>"
                            + "<td>" + train.getTr_no() + "</td>"
                            + "<td>" + train.getFrom_stn() + "</td>"
                            + "<td>" + train.getTo_stn() + "</td>"
                            + "<td>" + time + "</td>"
                            + "<td>" + train.getSeats() + "</td>"
                            + "<td>" + train.getFare() + " RS</td>"
                            + "<td><a href='booktrainbyref?trainNo=" + train.getTr_no()
                            + "&fromStn=" + train.getFrom_stn() + "&toStn=" + train.getTo_stn()
                            + "'><div class='book-btn'>Book Now</div></a></td></tr>");
                }
            } else {
                pw.println("<tr><td colspan='8' style='text-align:center; font-weight:bold;'>"
                        + "No trains available between " + fromStation + " and " + toStation + "</td></tr>");
            }

            pw.println("</table>");
            pw.println("</div>"); // Closing white-box div
        } catch (Exception e) {
            throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
        }
    }
}
