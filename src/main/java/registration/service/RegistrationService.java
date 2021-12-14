package registration.service;

import java.util.Date;
import java.util.List;

import helpers.app.AppHelpers;
import helpers.app.AppResponse;
import products.dao.ProductsDao;
import registration.dao.RegistrationDao;
import registration.model.Registrations;

public class RegistrationService {
	private RegistrationDao registrationDao;
	private ProductsDao productsDao;

	public RegistrationService() {
		registrationDao = RegistrationDao.getRegistrationDaoInstance();
		productsDao = ProductsDao.getProductsDaoInstance();
	}

	public AppResponse<Integer> createRegistration(int userID, String serialNo,String name, Date purchaseDate) {
		//Check Valid Serial Number
		int checkProduct = productsDao.getProductBySerial(serialNo);
		if (checkProduct == AppHelpers.INVALID_PRODUCT) {
			return new AppResponse<Integer>(0, false);
		}

		// Product Registered Already
		Registrations registeredAlready = registrationDao.validateRegistrationDetails(userID,-1,checkProduct);
		if (registeredAlready != null) {
			return new AppResponse<Integer>(0, false);
		}

		Registrations newReg=new Registrations(0,userID, checkProduct, name, purchaseDate, null, null, null); // Dates will be current Time-stamp by default
		int status = registrationDao.createProductRegistration(newReg);
		return new AppResponse<Integer>(status, false);
	}

	public AppResponse<List<Registrations>> getUserRegistrations(int userID) {
		List<Registrations> list = registrationDao.getUserRegistrations(userID);
		return new AppResponse<List<Registrations>>(list, list.size() == 0);
	}

}
