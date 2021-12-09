package helpers.env.local;

import helpers.env.Environment;

public class LocalEnv implements Environment {
	@Override
	public String getDatabaseURL() {
		return "jdbc:mysql://localhost:3306/j2ee-insurance?autoReconnect=true&useSSL=false";
	}
	
	@Override
	public String getDBUser() {
		// TODO Auto-generated method stub
		return "chaitanya";
	}
	
	@Override
	public String getDBPass() {
		return "123";
	}
	
	@Override
	public boolean isDev() {
		return true;
	}
	
	@Override
	public boolean isHeroku() {
		return false;
	}
	@Override
	public String getDriver() {
		// TODO Auto-generated method stub
		return "com.mysql.jdbc.Driver";
	}
}
