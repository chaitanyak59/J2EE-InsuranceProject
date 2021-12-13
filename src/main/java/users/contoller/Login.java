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

import helpers.app.AppHelpers;
import helpers.app.AppResponse;
import helpers.env.Environment;
import helpers.env.Factory;
import users.model.Users;
import users.service.UsersService;

/**
 * Servlet implementation class login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersService userSvc;
	private Environment env;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		userSvc = new UsersService();
		env = Factory.getEnvInstance();
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
				 Cookie user = AppHelpers.createCookie("user", res.getPayload().getName(), env.isHeroku(), true, 86400, null, "/");
		         Cookie role =AppHelpers.createCookie("role_id",String.valueOf(res.getPayload().getRole_id()), env.isHeroku(), true, 86400, null, "/");
		         response.addCookie(user);
		         response.addCookie(role);
		         response.sendRedirect("Home"); // Home Servlet
			}
		}
		
	}

}
