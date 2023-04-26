package com.mindtree.dao;

import com.mindtree.entity.Gym;
import com.mindtree.exception.GymAppDaoException;

public interface GymDao {

	public Gym getGymByID(byte id) throws GymAppDaoException;

}
