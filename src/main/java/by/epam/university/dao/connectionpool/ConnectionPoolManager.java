package by.epam.university.dao.connectionpool;

public class ConnectionPoolManager {
	private static volatile ConnectionPoolManager instance;

	public static ConnectionPoolManager getInstance() {
		ConnectionPoolManager localInstance = instance;
		if (localInstance == null) {
			synchronized (ConnectionPoolManager.class) {
				if (localInstance == null) {
					instance = localInstance = new ConnectionPoolManager();
				}
			}
		}
		return localInstance;
	}

	public ConnectionPool getConnectionPool() {
		return ConnectionPool.getInstance();
	}

}
