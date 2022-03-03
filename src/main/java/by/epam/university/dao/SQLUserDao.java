package by.epam.university.dao;

import java.util.List;

import by.epam.university.dao.exception.DAOException;
import by.epam.university.entity.User;

public interface SQLUserDao {

	boolean insert(User user) throws DAOException;

	User getUserByLoginPassword(String login, String password) throws DAOException;

	User getUserByLogin(String login) throws DAOException;

	User getUserById(int id) throws DAOException;
	
	List<User> getAll() throws DAOException;
	
	boolean updateUser(User user) throws DAOException;

}
