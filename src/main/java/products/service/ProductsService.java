package products.service;

import java.util.List;

import helpers.app.AppHelpers;
import helpers.app.AppResponse;
import products.dao.ProductsDao;
import products.model.Products;

public class ProductsService {
	private ProductsDao productsDao;
	
	public ProductsService() {
		productsDao = ProductsDao.getProductsDaoInstance();
	}
	
	public AppResponse<Integer> createProduct(String name, String type, String serial_no, String price) {
		//Check Product Already Registered
		int isProductRegistered = productsDao.getProductBySerial(serial_no);
		if (isProductRegistered != AppHelpers.INVALID_PRODUCT) {
			return new AppResponse<Integer>(0, false);
		}
		Products product=new Products(0, name, type, serial_no, price, null);
		int status = productsDao.createProduct(product);
		return new AppResponse<Integer>(status, false);
	}
	
	public AppResponse<Integer> deleteProduct(int productID) {
		int status = productsDao.deleteProduct(productID);
		return new AppResponse<Integer>(status, false);
	}
	
	public AppResponse<List<Products>> getAllProducts(String searchParam) {
		List<Products> list = productsDao.getAllProducts(searchParam);
		return new AppResponse<List<Products>>(list, list.size() == 0);
	}
}
