package helpers.env.heroku;

import helpers.env.Environment;

public class HerokuEnv implements Environment {
	@Override
	public String getDatabaseURL() {
		return System.getenv("CLEARDB_DATABASE_URL");
	}
	
	@Override
	public String getDBPass() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getDBPort() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getDBUser() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean isDev() {
		return false;
	}
	
	@Override
	public boolean isHeroku() {
		return true;
	}
	@Override
	public String getDriver() {
		// TODO Auto-generated method stub
		return "com.mysql.jdbc.Driver";
	}
}
