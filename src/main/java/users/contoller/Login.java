package users.contoller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.app.AppResponse;
import users.model.Users;
import users.service.UsersService;

/**
 * Servlet implementation class login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersService userSvc;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		userSvc = new UsersService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		String url = "login.jsp?authenticated=";
		RequestDispatcher rd;
		if (errors.size() > 0) {
			rd = request.getRequestDispatcher(url + "false");
			rd.forward(request, response);
		} else {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			AppResponse<Users> res = userSvc.authenticateUser(email, password);
			if (res.hasError() || res.getPayload() == null) {
				rd = request.getRequestDispatcher(url + "false");
				rd.forward(request, response);
			}else {
				//Valid redirect to home page & set cookie
				 Cookie c1=new Cookie("user",res.getPayload().getName());
		         Cookie c2=new Cookie("role_id",String.valueOf(res.getPayload().getRole_id()));
		         c1.setPath("/");
		         c1.setMaxAge(86400);
		         c1.setHttpOnly(true);
		         c2.setPath("/");
		         c2.setMaxAge(86400);
		         c2.setHttpOnly(true);
		         response.addCookie(c2);
		         response.addCookie(c1);
		         response.sendRedirect("Home"); // Home Servlet
			}
		}
		
	}

}
