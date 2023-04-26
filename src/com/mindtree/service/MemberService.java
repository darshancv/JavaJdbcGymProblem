package com.mindtree.service;

import java.util.List;
import java.util.Set;

import com.mindtree.entity.Member;
import com.mindtree.exception.GymAppServiceException;

public interface MemberService {

	public Set<Member> insertMembers(Set<Member> members) throws GymAppServiceException;

	public Set<Member> getMembersByGymID(byte id) throws GymAppServiceException;

	public String updateMemberHeightByID(byte id, float height) throws GymAppServiceException;

	public List<Member> getAllMembers() throws GymAppServiceException;

	public List<Member> ListAllDetailsUsingage(int age) throws GymAppServiceException;



}
