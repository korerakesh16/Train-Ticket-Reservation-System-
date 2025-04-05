package com.train.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.train.constant.UserRole;
import com.train.utility.TrainUtil;

@SuppressWarnings("serial")
@WebServlet("/booktrainbyref")
public class BookTrainByRef extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);

        String emailId = TrainUtil.getCurrentUserEmail(req);
        long trainNo = Long.parseLong(req.getParameter("trainNo"));
        int seat = 1;
        String fromStn = req.getParameter("fromStn");
        String toStn = req.getParameter("toStn");
        LocalDate journeyDate = LocalDate.now();

        RequestDispatcher rd = req.getRequestDispatcher("UserViewTrains.html");
        rd.include(req, res);

        pw.println("<html><head><title>Ticket Booking</title>");
        pw.println("<style>"
                + "body { font-family: 'Poppins', sans-serif; background: linear-gradient(to bottom, #1a1a1a, #8B0000); color: white; text-align: center; }"
                + ".container { width: 60%; margin: 20px auto; padding: 20px; background: white; border-radius: 10px; box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.5); color: black; }"
                + ".title { font-size: 24px; font-weight: bold; color: #8B0000; margin-bottom: 15px; }"
                + "table { width: 100%; border-collapse: collapse; background: white; color: black; border-radius: 8px; overflow: hidden; margin-top: 10px; }"
                + "th, td { padding: 12px; border: 1px solid #ccc; text-align: center; }"
                + "input, select { width: 90%; padding: 8px; border-radius: 5px; border: 1px solid #ccc; background: white; color: black; }"
                + "input[type='date'], input[type='number'] { cursor: pointer; }"
                + ".btn-primary { padding: 10px 20px; border-radius: 8px; background: #8B0000; color: white; font-weight: bold; border: none; transition: 0.3s; cursor: pointer; margin-top: 15px; }"
                + ".btn-primary:hover { background: #A52A2A; transform: scale(1.05); }"
                + "</style></head><body>");

        pw.println("<div class='container'>");
        pw.println("<h2 class='title'>Your Ticket Booking Information</h2>");
        pw.println("<form action='payment' method='post'>");
        pw.println("<table>");
        pw.println("<tr><th>User ID</th><td>" + emailId + "</td><th>Train No</th><td>" + trainNo + "</td></tr>");
        pw.println("<tr><th>From Station</th><td>" + fromStn + "</td><th>To Station</th><td>" + toStn + "</td></tr>");
        pw.println("<tr><th>Journey Date</th><td><input type='date' name='journeydate' value='" + journeyDate + "'></td>");
        pw.println("<th>No of Seats</th><td><input type='number' name='seats' value='" + seat + "'></td></tr>");
        pw.println("<tr><th>Select Class</th><td>"
                + "<select name='class' required>"
                + "<option value='Sleeper(SL)'>Sleeper (SL)</option>"
                + "<option value='Second Sitting(2S)'>Second Sitting (2S)</option>"
                + "<option value='AC First Class(1A)'>AC First Class (1A)</option>"
                + "<option value='AC 2 Tier(2A)'>AC 2 Tier (2A)</option>"
                + "</select></td>");
        pw.println("<th>Berth Preference</th><td>"
                + "<select name='berth'>"
                + "<option value='NO'>No Preference</option>"
                + "<option value='LB'>Lower Berth (LB)</option>"
                + "<option value='UB'>Upper Berth (UB)</option>"
                + "<option value='C'>Cabin</option>"
                + "</select></td></tr>");
        pw.println("</table>");
        pw.println("<input type='submit' class='btn-primary' value='Pay And Book'>");
        pw.println("</form></div></body></html>");

        pw.close();
    }
}
