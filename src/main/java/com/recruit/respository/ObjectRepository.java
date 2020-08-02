package com.recruit.respository;

import java.util.List;

/**
 * Interface to acheive abstraction.Defining all Application related operations
 * 
 * @author Abhinav Singh
 *
 * @param <T> It can be of type an Object like JobOffer/JobApplication
 */
public interface ObjectRepository<T> {

	public Integer create(T t) throws Exception;

	public T getById(int id) throws Exception;

	public List<T> getAll();

	public void updateById(T t, int id) throws Exception;
}
