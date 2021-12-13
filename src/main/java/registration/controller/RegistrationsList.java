package registration.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.app.AppHelpers;
import helpers.app.AppResponse;
import registration.service.RegistrationService;

/**
 * Servlet implementation class RegistrationsList
 */
@WebServlet("/RegistrationsList")
public class RegistrationsList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegistrationService registrationService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationsList() {
		super();
		registrationService = new RegistrationService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Authenticated Request
		int userID = getUserID(request, response);
		if (userID == -1) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Request/Please Login");
			return;
		}
		AppResponse<List<registration.model.Registrations>> res = registrationService.getAllRegistrations(userID);
		request.setAttribute("registrations", res.getPayload());
		RequestDispatcher rd = request.getRequestDispatcher("/registrations-list.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private int getUserID(HttpServletRequest request, HttpServletResponse response) {
		int userID = -1;
		Optional<String> userCookie = AppHelpers.getCookie(request, AppHelpers.USER_ID);
		if (userCookie.isPresent()) {
			userID = Integer.valueOf(userCookie.get());
		}
		return userID;
	}

}
