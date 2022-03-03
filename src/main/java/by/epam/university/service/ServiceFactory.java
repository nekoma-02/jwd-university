package by.epam.university.service;

import by.epam.university.service.impl.AdminServiceImpl;
import by.epam.university.service.impl.ApplicationServiceImpl;
import by.epam.university.service.impl.UserServiceImpl;

public class ServiceFactory {
	
	private static volatile ServiceFactory instance;

	public static ServiceFactory getInstance() {
		ServiceFactory localInstance = instance;
		if (localInstance == null) {
			synchronized (ServiceFactory.class) {
				if (localInstance == null) {
					instance = localInstance = new ServiceFactory();
				}
			}
		}
		return localInstance;
	}

	public UserService getUserService() {
		return new UserServiceImpl();
	}

	public ApplicationService getApplicationService() {
		return new ApplicationServiceImpl();
	}
	
	public AdminService getAdminService() {
		return new AdminServiceImpl();
	}
}
