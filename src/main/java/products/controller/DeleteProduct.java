package products.controller;

import java.io.IOException;
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
 * Servlet implementation class DeleteProduct
 */
@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductsService productsSvc;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProduct() {
        super();
        productsSvc = new ProductsService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isAdmin = validateRequest(request, response);
		if(!isAdmin) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Request/Please Login with Admin credentials");
		    return;
		}
		
		String url = "Products?status=";	
		String id = request.getParameter("id");
		if(id == null) {
			response.sendRedirect(url+"false");
			return;
		}
		int deleteID = Integer.parseInt(id);
		AppResponse<Integer> res = productsSvc.deleteProduct(deleteID);
		if(res.hasError() || res.getPayload() == 0) {
			response.sendRedirect(url+"false");
			return;
		} else {
			response.sendRedirect(url+"true");
			return;
		}
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
