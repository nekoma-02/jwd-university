package by.epam.university.dao.connectionpool;

import java.util.ResourceBundle;

public class DBResourcesManager {
	private final static DBResourcesManager instance = new DBResourcesManager();
	
	private ResourceBundle bundle = ResourceBundle.getBundle("db");

	public static DBResourcesManager getInstance() {
		return instance;
	}
	
	public String getValue(String key) {
		return bundle.getString(key);
	}
	
}
