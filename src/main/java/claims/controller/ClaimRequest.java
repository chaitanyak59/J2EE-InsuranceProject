package claims.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.app.AppHelpers;

/**
 * Servlet implementation class ClaimRequest
 */
@WebServlet("/ClaimRequest")
public class ClaimRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ClaimRequest() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userID = getUserID(request, response);
		if(userID == -1) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Request/Please Login");
		    return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("create-claim.jsp?id="+request.getParameter("id"));
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
