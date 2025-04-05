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
@WebServlet("/adminviewtrainfwd")
public class AdminViewTrainFwd extends HttpServlet {

	private TrainService trainService = new TrainServiceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		TrainUtil.validateUserAuthorization(req, UserRole.ADMIN);
		
		try {
			List<TrainBean> trains = trainService.getAllTrains();
			RequestDispatcher rd = req.getRequestDispatcher("ViewTrains.html");
			rd.include(req, res);

			pw.println("<style>");
			pw.println("body { font-family: 'Poppins', sans-serif; background: linear-gradient(to bottom, #000000, #8B0000); color: white; text-align: center; padding-top: 20px; }");
			pw.println(".table-container { margin: 20px auto; width: 85%; background: white; padding: 15px; border-radius: 10px; box-shadow: 0px 4px 10px rgba(255, 215, 0, 0.5); }");
			pw.println(".styled-table { width: 100%; border-collapse: collapse; color: black; }");
			pw.println(".styled-table th, .styled-table td { padding: 12px; border: 1px solid black; text-align: center; font-size: 16px; }");
			pw.println(".styled-table th { background: #FFD700; color: black; font-size: 18px; }");
			pw.println(".styled-table tr:nth-child(even) { background: #f2f2f2; }");
			pw.println(".styled-table tr:hover { background: #ddd; transition: 0.3s; }");
			pw.println(".view-btn { text-decoration: none; padding: 8px 15px; border-radius: 5px; background: #FFD700; color: black; font-weight: bold; border: 2px solid #FFC107; }");
			pw.println(".view-btn:hover { background: #FFC107; color: white; }");
			pw.println(".update-btn { text-decoration: none; padding: 8px 15px; border-radius: 5px; background: red; color: white; font-weight: bold; border: 2px solid darkred; }");
			pw.println(".update-btn:hover { background: darkred; color: white; }");
			pw.println("</style>");
			
			pw.println("<div class='table-container'>");
			pw.println("<h2 style='color: black;'>Available Trains</h2>");
			pw.println("<table class='styled-table'>");
			pw.println("<tr><th>Train Name</th><th>Train Number</th><th>From Station</th><th>To Station</th><th>Seats Available</th><th>Fare (INR)</th><th>Action</th></tr>");

			if (trains != null && !trains.isEmpty()) {
				for (TrainBean train : trains) {
					pw.println("<tr>");
					pw.println("<td><a href='viewadmin?trainNo=" + train.getTr_no() + "&fromStn=" + train.getFrom_stn() + "&toStn=" + train.getTo_stn() + "' class='view-btn'>" + train.getTr_name() + "</a></td>");
					pw.println("<td>" + train.getTr_no() + "</td>");
					pw.println("<td>" + train.getFrom_stn() + "</td>");
					pw.println("<td>" + train.getTo_stn() + "</td>");
					pw.println("<td>" + train.getSeats() + "</td>");
					pw.println("<td>" + train.getFare() + " RS</td>");
					pw.println("<td><a href='adminupdatetrain?trainnumber=" + train.getTr_no() + "' class='update-btn'>Update</a></td>");
					pw.println("</tr>");
				}
			} else {
				pw.println("<tr><td colspan='7' style='color: red; font-size: 18px; font-weight: bold;'>No Running Trains</td></tr>");
			}

			pw.println("</table></div>");

		} catch (Exception e) {
			throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
		}
	}
}
