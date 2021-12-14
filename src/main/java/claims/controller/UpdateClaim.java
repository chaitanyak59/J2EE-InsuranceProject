package claims.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import claims.service.ClaimsService;
import helpers.app.AppHelpers;

/**
 * Servlet implementation class UpdateClaim
 */
@WebServlet("/UpdateClaim")
public class UpdateClaim extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClaimsService claimsService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateClaim() {
        super();
        claimsService = new ClaimsService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isAdmin = validateRequest(request, response);
		if(!isAdmin) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Request");
		    return;
		}
		
		String claimID = request.getParameter("id");
		String status = request.getParameter("approve");
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean validateRequest(HttpServletRequest request, HttpServletResponse response) {
		boolean isAdmin = false;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(AppHelpers.USER_ROLE)) {
					isAdmin = AppHelpers.isAdmin(Integer.valueOf(cookie.getValue()));
					break;
				}
			}
		}
		return isAdmin;
	}

}
