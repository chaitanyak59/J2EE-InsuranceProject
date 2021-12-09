package helpers.env;

public interface Environment {
	public String getDatabaseURL();
	public String getDBUser();
	public String getDBPass();
	public boolean isHeroku();
	public boolean isDev();
	public String getDriver();
}
