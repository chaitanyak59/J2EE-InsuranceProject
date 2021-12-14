package claims.service;

import java.util.Date;
import java.util.List;

import claims.dao.ClaimsDao;
import claims.model.Claims;
import helpers.app.AppHelpers;
import helpers.app.AppResponse;
import registration.dao.RegistrationDao;
import registration.model.Registrations;

public class ClaimsService {

	private ClaimsDao claimsDao;
	private RegistrationDao registrationDao;

	public ClaimsService() {
		claimsDao = ClaimsDao.getClaimsDaoInstance();
		registrationDao = RegistrationDao.getRegistrationDaoInstance();
	}

	public AppResponse<Integer> createClaimRequest(int userID, int regID, String category, String description) {
		//Is Valid Product and registered For User
		Registrations existnReg = registrationDao.validateRegistrationDetails(userID, regID, -1);
		if (existnReg == null) {
			return new AppResponse<Integer>(0, true);
		}

		//Is Policy Expired?
		if(AppHelpers.getDiffYears(existnReg.getRegistrationDate(), new Date()) > 5) {
			return new AppResponse<Integer>(0, true);
		}

		//Check Claim Counter 3
		int count = claimsDao.getClaimCountByRegistration(regID);
		if(count >= 3) {
			return new AppResponse<Integer>(0, true);
		}

		Claims claim = new Claims(-1, existnReg.getId(), false, null, description,category); // Record id will not be created
		int status = claimsDao.createClaimRequest(claim);
		return new AppResponse<Integer>(status, false);
	}

	public AppResponse<List<Claims>> getUserClaims(int userID) {
		List<Claims> list = claimsDao.getUserClaims(userID);
		return new AppResponse<List<Claims>>(list, list.size() == 0);
	}

	public AppResponse<List<Claims>> getAllClaims() {
		List<Claims> list = claimsDao.getAllClaims();
		return new AppResponse<List<Claims>>(list, list.size() == 0);
	}

}
