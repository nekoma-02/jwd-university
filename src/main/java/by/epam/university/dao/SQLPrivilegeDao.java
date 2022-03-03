package by.epam.university.dao;

import java.util.List;

import by.epam.university.dao.exception.DAOException;
import by.epam.university.entity.Privilege;

public interface SQLPrivilegeDao {

	List<Privilege> getAll() throws DAOException;

	Privilege getByName(String name) throws DAOException;
	
	Privilege getById(int id) throws DAOException;
	
	boolean insert(Privilege privilege) throws DAOException;
	
	boolean update(Privilege privilege) throws DAOException;
	
	boolean remove(int idPrivilege) throws DAOException;
}
