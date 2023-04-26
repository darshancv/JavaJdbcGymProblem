package com.mindtree.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mindtree.dao.GymDao;
import com.mindtree.entity.Gym;
import com.mindtree.exception.GymAppDaoException;
import com.mindtree.utility.GymAppUtil;

public class GymDaoImpl implements GymDao {

	@Override
	public Gym getGymByID(byte id) throws GymAppDaoException {
		Gym gym=null;
		String query="select * from gym where gymid="+id;
		Connection connection=GymAppUtil.getConnection();
		PreparedStatement statement =null;
		ResultSet set=null;
		try {
			statement = connection.prepareStatement(query);
			 set=statement.executeQuery();
			set.next();
			gym=new Gym(set.getByte(1),set.getString(2));
			return gym;
		} catch (SQLException e) {
			
			throw new GymAppDaoException("Dao: Please check the Id what you have entered");
		}
		finally {
		
				GymAppUtil.closingResources(set);
				GymAppUtil.closingResources(statement);
				GymAppUtil.closeDB(connection);
			
		}
		
		
		
	}

}
