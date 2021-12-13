package registration.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
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
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegistrationService registrationSvc;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        registrationSvc = new RegistrationService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Authenticated Request
		int userID = getUserID(request, response);
		if(userID == -1) {
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
		
		RequestDispatcher rd;
		String url = "/registration.jsp?status=";
		if(errors.size() > 0) {
			rd = request.getRequestDispatcher(url + "false");
			rd.forward(request, response);
			return;
		}
		Date purchaseDate = null;
		String serialNo = request.getParameter("serialNo");
		String purchaseDateValue = request.getParameter("purchase_date");
		String name = request.getParameter("name");
		
		try {
			purchaseDate = new SimpleDateFormat("yyyy-MM-d", Locale.ENGLISH).parse(purchaseDateValue);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Not allowing Future Dates
		if(purchaseDate.after(new Date())) {
			rd = request.getRequestDispatcher(url + "false");
			rd.forward(request, response);
			return;
		}

		AppResponse<Integer> res = registrationSvc.createRegistration(userID, serialNo,name, purchaseDate);
		if(res.hasError() || res.getPayload() == 0) {
			rd = request.getRequestDispatcher(url + "false");
			rd.forward(request, response);
			return;
		}
		rd = request.getRequestDispatcher(url + "true");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
