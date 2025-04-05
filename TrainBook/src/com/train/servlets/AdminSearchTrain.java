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
@WebServlet("/adminsearchtrain")
public class AdminSearchTrain extends HttpServlet {

	private TrainService trainService = new TrainServiceImpl();

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		TrainUtil.validateUserAuthorization(req, UserRole.ADMIN);
		try {
			String trainNo = req.getParameter("trainnumber");
			TrainBean train = trainService.getTrainById(trainNo);
			RequestDispatcher rd = req.getRequestDispatcher("AdminSearchTrain.html");
			rd.include(req, res);
			
			pw.println("<div class='container mt-4'>");

			if (train != null) {
				pw.println("<h3 class='text-center text-warning'>Searched Train Details</h3>");
				pw.println("<table class='table table-bordered table-striped'>");
				pw.println("<thead class='table-dark'><tr><th>Attribute</th><th>Details</th></tr></thead>");
				pw.println("<tbody>");
				pw.println("<tr><td>Train Name</td><td>" + train.getTr_name() + "</td></tr>");
				pw.println("<tr><td>Train Number</td><td>" + train.getTr_no() + "</td></tr>");
				pw.println("<tr><td>From Station</td><td>" + train.getFrom_stn() + "</td></tr>");
				pw.println("<tr><td>To Station</td><td>" + train.getTo_stn() + "</td></tr>");
				pw.println("<tr><td>Available Seats</td><td>" + train.getSeats() + "</td></tr>");
				pw.println("<tr><td>Fare (INR)</td><td>" + train.getFare() + " RS</td></tr>");
				pw.println("</tbody></table>");
			} else {
				pw.println("<div class='alert alert-danger text-center'>Train No. " + trainNo + " is Not Available!</div>");
			}

			pw.println("</div>"); // Closing container div

		} catch (Exception e) {
			throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
		}
	}
}
