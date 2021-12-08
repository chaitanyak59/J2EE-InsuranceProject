package users.contoller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.app.AppResponse;
import users.service.UsersService;

/**
 * Servlet implementation class Create
 */
@WebServlet("/Create")
public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersService userSvc;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Create() {
        super();
        userSvc = new UsersService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		String url = "create-account.jsp?status=";
		if(errors.size() > 0) {
			response.sendRedirect(url+"false");
			return;
		}
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String mobileNo = request.getParameter("mobileNo");
		String address = request.getParameter("address");
		String password = request.getParameter("password");
		AppResponse<Integer> res = userSvc.createUserAccount(name, email, password, mobileNo, address);
		if(res.hasError() || res.getPayload() == 0) {
			response.sendRedirect(url+"false");
			return;
		}
		response.sendRedirect(url+"true");
	}

}
