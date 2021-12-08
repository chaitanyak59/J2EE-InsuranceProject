package helpers.env;

import helpers.env.heroku.HerokuEnv;
import helpers.env.local.LocalEnv;

public class Factory {
	public static Environment getEnvInstance() {
		if (System.getenv("HEROKU") != null) {
			return new HerokuEnv();
		} else {
			return new LocalEnv();
		}
	}
}
