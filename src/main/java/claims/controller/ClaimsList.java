package claims.controller;

import java.io.IOException;
import java.util.List;
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
 * Servlet implementation class ClaimsList
 */
@WebServlet("/ClaimsList")
public class ClaimsList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClaimsService claimsService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClaimsList() {
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
		int roleID = getUserDetails(request, response, AppHelpers.USER_ROLE);
		if (roleID == -1) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Request/Please Login");
			return;
		}

		int userID = getUserDetails(request, response, AppHelpers.USER_ID);
		String searchParameter = request.getParameter("search");
		AppResponse<List<claims.model.Claims>> res;
		if (AppHelpers.isAdmin(roleID)) {
			res = claimsService.getAllClaims(searchParameter);
			request.setAttribute("claims_list", res.getPayload());
			RequestDispatcher rd = request.getRequestDispatcher("/claims-manage.jsp");
			rd.forward(request, response);
		} else {
			res = claimsService.getUserClaims(userID, searchParameter);
			request.setAttribute("claims_list", res.getPayload());
			RequestDispatcher rd = request.getRequestDispatcher("/user-claims.jsp");
			rd.forward(request, response);
		}
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

	private int getUserDetails(HttpServletRequest request, HttpServletResponse response, String cookieName) {
		int value = -1;
		Optional<String> userCookie = AppHelpers.getCookie(request, cookieName);
		if (userCookie.isPresent()) {
			value = Integer.valueOf(userCookie.get());
		}
		return value;
	}

}
