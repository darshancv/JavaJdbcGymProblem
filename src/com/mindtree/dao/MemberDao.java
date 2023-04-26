package com.mindtree.dao;

import java.util.List;
import java.util.Set;

import com.mindtree.entity.Member;
import com.mindtree.exception.GymAppDaoException;

public interface MemberDao {

	

	public Set<Member> insertMembers(Set<Member> members) throws GymAppDaoException;

	public Set<Member> getMembersByGymID(byte id) throws GymAppDaoException;

	public String updateMemberHeightByID(byte id, float height) throws GymAppDaoException;

	public List<Member> getAllMembers() throws GymAppDaoException;

	public List<Member> listAllDetailsUsingage(int age) throws GymAppDaoException;

}
