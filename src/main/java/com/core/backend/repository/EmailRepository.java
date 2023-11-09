package com.core.backend.repository;

import java.util.Date;

import org.apache.ibatis.exceptions.PersistenceException;

public interface EmailRepository {

	public Date getDateNow() throws PersistenceException;	

}
