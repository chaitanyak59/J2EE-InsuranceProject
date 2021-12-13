package products.controller;

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
import products.service.ProductsService;

/**
 * Servlet implementation class CreateProduct
 */
@WebServlet("/CreateProduct")
public class CreateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductsService productsSvc;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProduct() {
        super();
        productsSvc = new ProductsService();
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
		boolean isAdmin = validateRequest(request, response);
		if(!isAdmin) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Request/Please Login with Admin credentials");
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
		String url = "/create-product.jsp?status=";
		if(errors.size() > 0) {
			rd = request.getRequestDispatcher(url + "false");
			rd.forward(request, response);
			return;
		}
		
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String serial_no = request.getParameter("serial_no");
		String price = request.getParameter("price");
		AppResponse<Integer> res = productsSvc.createProduct(name, type, serial_no, price);
		if(res.hasError() || res.getPayload() == 0) {
			rd = request.getRequestDispatcher(url + "false");
			rd.forward(request, response);
			return;
		}
		rd = request.getRequestDispatcher(url + "true");
		rd.forward(request, response);
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
