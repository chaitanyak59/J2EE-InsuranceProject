package helpers.env.heroku;

import helpers.env.Environment;

public class HerokuEnv implements Environment {
	@Override
	public String getDatabaseURL() {
		return System.getenv("DB_URL");
	}
	
	@Override
	public String getDBPass() {
		return System.getenv("DB_PASS");
	}
	@Override
	public String getDBUser() {
		return System.getenv("DB_USER");
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
		return System.getenv("DB_DRIVER");
	}
}
