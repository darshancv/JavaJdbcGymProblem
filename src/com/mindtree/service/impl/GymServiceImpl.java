package com.mindtree.service.impl;

import com.mindtree.dao.GymDao;
import com.mindtree.dao.impl.GymDaoImpl;
import com.mindtree.entity.Gym;
import com.mindtree.exception.GymAppDaoException;
import com.mindtree.exception.GymAppServiceException;
import com.mindtree.exception.GymNotFoundException;
import com.mindtree.service.GymService;

public class GymServiceImpl implements GymService {
	GymDao gymDao=new GymDaoImpl();
	
	@Override
	public Gym getGymByID(byte id) throws GymAppServiceException {

		try {
			Gym gym = gymDao.getGymByID(id);
			if (gym != null) {
				return gym;
			}
			else
			{
				throw new GymNotFoundException("Service: gym id not found");
			}
		} catch (GymAppDaoException e) {
			throw new GymAppServiceException(e.getMessage());
		}
	
	
	}

}
