package com.mindtree.service;

import com.mindtree.entity.Gym;
import com.mindtree.exception.GymAppServiceException;

public interface GymService {

	public Gym getGymByID(byte id) throws GymAppServiceException;

}
