package products.controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class Products
 */
@WebServlet("/Products")
public class Products extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductsService productsSvc;
    
    public Products() {
        super();
        productsSvc = new ProductsService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isAdmin = validateRequest(request, response);
		if(!isAdmin) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Request");
		    return;
		}
		
		AppResponse<List<products.model.Products>> res = productsSvc.getAllProducts();
		request.setAttribute("productLists", res.getPayload());
		RequestDispatcher rd = request.getRequestDispatcher("/products-list.jsp");
		rd.forward(request, response);
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
