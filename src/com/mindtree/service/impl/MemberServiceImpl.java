package com.mindtree.service.impl;

import java.util.List;
import java.util.Set;

import com.mindtree.dao.MemberDao;
import com.mindtree.dao.impl.MemberDaoImpl;
import com.mindtree.entity.Member;
import com.mindtree.exception.GymAppDaoException;
import com.mindtree.exception.GymAppServiceException;
import com.mindtree.exception.MemberIdNotFoundException;
import com.mindtree.service.MemberService;

public class MemberServiceImpl implements MemberService {
	MemberDao memberDao = new MemberDaoImpl();

	@Override
	public Set<Member> insertMembers(Set<Member> members) throws GymAppServiceException {
		try {
			return memberDao.insertMembers(members);
		} catch (GymAppDaoException e) {
			throw new GymAppServiceException(e.getMessage());
		}
	}

	@Override
	public Set<Member> getMembersByGymID(byte id) throws GymAppServiceException {

		try {
			Set<Member> members = memberDao.getMembersByGymID(id);
			if (members.size() > 0) {
				return members;
			} else {
				throw new MemberIdNotFoundException("Service: ID is wrong please check once");
			}

		} catch (GymAppDaoException e) {

			throw new GymAppServiceException(e.getMessage());
		}

	}

	@Override
	public String updateMemberHeightByID(byte id, float height) throws GymAppServiceException {

		try {
			return memberDao.updateMemberHeightByID(id, height);
		} catch (GymAppDaoException e) {

			throw new MemberIdNotFoundException("Member id is incorrect please check once");
		}

	}

	@Override
	public List<Member> getAllMembers() throws GymAppServiceException {
		try {
			return memberDao.getAllMembers();
		} catch (GymAppDaoException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			throw new GymAppServiceException(e.getMessage());
		}
	}

	@Override
	public List<Member> ListAllDetailsUsingage(int age) throws GymAppServiceException {
		try {
			return memberDao.listAllDetailsUsingage(age);
		} catch (GymAppDaoException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			throw new GymAppServiceException(e.getMessage());
		}

	}

}
