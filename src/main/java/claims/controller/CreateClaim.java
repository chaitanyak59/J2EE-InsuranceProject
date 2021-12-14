package claims.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import claims.service.ClaimsService;
import helpers.app.AppHelpers;
import helpers.app.AppResponse;

/**
 * Servlet implementation class CreateClaim
 */
@WebServlet("/CreateClaim")
public class CreateClaim extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClaimsService claimsService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateClaim() {
		super();
		claimsService = new ClaimsService();
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

		Enumeration<String> paramNames = request.getParameterNames();
		// Error Checking... If any details are missing error will be thrown
		ArrayList<String> errors = new ArrayList<String>();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String value = request.getParameter(paramName);
			if (value.isEmpty()) {
				errors.add(paramName);
			}
		}
		
		String registrationID = request.getParameter("reg_id");

		RequestDispatcher rd;
		String url = String.format("/create-claim.jsp?id=%s&status=", registrationID);
		if (errors.size() > 0) {
			rd = request.getRequestDispatcher(url + "false");
			rd.forward(request, response);
			return;
		}
		
		String category = request.getParameter("category");
		String description = request.getParameter("description");
		
		AppResponse<Integer> res = claimsService.createClaimRequest(userID, Integer.valueOf(registrationID), category, description);
		if(res.hasError() || res.getPayload() == 0) {
			rd = request.getRequestDispatcher(url + "false");
			rd.forward(request, response);
			return;
		}
		rd = request.getRequestDispatcher(url + "true");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
