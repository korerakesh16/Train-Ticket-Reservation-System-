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
import com.train.service.TrainService;
import com.train.service.impl.TrainServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/adminupdatetrain")
public class AdminTrainUpdate extends HttpServlet {

	private TrainService trainService = new TrainServiceImpl();

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();

		try {
			String trainNo = req.getParameter("trainnumber");
			TrainBean train = trainService.getTrainById(trainNo);
			RequestDispatcher rd = req.getRequestDispatcher("AdminHome.html");
			rd.include(req, res);
			
			pw.println("<div class='container mt-4'>");

			if (train != null) {
				pw.println("<h3 class='text-center text-primary'>Update Train Schedule</h3>");
				pw.println("<form action='updatetrainschedule' method='post'>");
				pw.println("<table class='table table-bordered'>");
				
				pw.println("<tr><td><b>Train Number:</b></td><td><input type='text' class='form-control' name='trainno' value='" + train.getTr_no() + "' readonly></td></tr>");
				pw.println("<tr><td><b>Train Name:</b></td><td><input type='text' class='form-control' name='trainname' value='" + train.getTr_name() + "'></td></tr>");
				pw.println("<tr><td><b>From Station:</b></td><td><input type='text' class='form-control' name='fromstation' value='" + train.getFrom_stn() + "'></td></tr>");
				pw.println("<tr><td><b>To Station:</b></td><td><input type='text' class='form-control' name='tostation' value='" + train.getTo_stn() + "'></td></tr>");
				pw.println("<tr><td><b>Available Seats:</b></td><td><input type='number' class='form-control' name='available' value='" + train.getSeats() + "'></td></tr>");
				pw.println("<tr><td><b>Fare (INR):</b></td><td><input type='text' class='form-control' name='fare' value='" + train.getFare() + "'></td></tr>");
				pw.println("<tr><td colspan='2' class='text-center'><input type='submit' class='btn btn-success' name='submit' value='Update Train Schedule'></td></tr>");
				
				pw.println("</table></form>");
			} else {
				RequestDispatcher errorPage = req.getRequestDispatcher("AdminUpdateTrain.html");
				errorPage.include(req, res);
				pw.println("<div class='alert alert-danger text-center'>Train No. " + trainNo + " is Not Available!</div>");
			}

			pw.println("</div>"); // Closing container div

		} catch (Exception e) {
			throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		doPost(req, res);
	}
}
