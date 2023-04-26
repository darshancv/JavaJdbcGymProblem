package com.mindtree.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.mindtree.dao.MemberDao;
import com.mindtree.entity.Gym;
import com.mindtree.entity.Member;
import com.mindtree.exception.GymAppDaoException;
import com.mindtree.utility.GymAppUtil;

public class MemberDaoImpl implements MemberDao {

	@Override
	public Set<Member> insertMembers(Set<Member> members) throws GymAppDaoException {
		Set<Member> set = new LinkedHashSet<>();
		String query = "insert into member(name,age,gender,height,gymid) values(?,?,?,?,?)";
		for (Member member : members) {

			Connection connection = GymAppUtil.getConnection();
			PreparedStatement statement = null;
			String name = member.getName();
			int age = member.getAge();
			String gender = member.getGender();
			float height = member.getHeight();
			int gymId = member.getGym().getgymId();
			try {
				statement = connection.prepareStatement(query);
				statement.setString(1, name);
				statement.setInt(2, age);
				statement.setString(3, gender);
				statement.setFloat(4, height);
				statement.setInt(5, gymId);
				int count = statement.executeUpdate();
				if (count == 1) {
					System.out.println(count + "row updated");
					set.add(member);
				}

			} catch (SQLException e) {
				throw new GymAppDaoException("Dao:Insertion not done please check your details");
			} finally {
				GymAppUtil.closingResources(statement);
				GymAppUtil.closeDB(connection);
			}
		}
		return set;
	}

	@Override
	public Set<Member> getMembersByGymID(byte id) throws GymAppDaoException {

		Set<Member> sets = new LinkedHashSet<>();
		String query = "select * from member where member.gymid=" + id;
		Connection connection = GymAppUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet set = null;
		try {
			statement = connection.prepareStatement(query);
			set = statement.executeQuery();
			while (set.next()) {
				Member member = new Member(set.getString(2), set.getInt(3), set.getString(4), set.getFloat(5));
				sets.add(member);
			}

		} catch (SQLException e) {
			throw new GymAppDaoException("Dao:please check the id");
		} finally {
			GymAppUtil.closingResources(set);
			GymAppUtil.closingResources(statement);
			GymAppUtil.closeDB(connection);
		}
		return sets;

	}

	@Override
	public String updateMemberHeightByID(byte id, float height) throws GymAppDaoException {

		String string = "";
		String query = "update member set height=" + height + " where memberId=" + id;
		Connection connection = GymAppUtil.getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(query);
			int count = statement.executeUpdate();
			if (count == 1) {
				string = count + "row updated";
			} else if (count == 0) {
				throw new GymAppDaoException("Wrong id");
			}
		} catch (SQLException e) {
			throw new GymAppDaoException("Dao:Please check the member Id");
		} finally {
			GymAppUtil.closingResources(statement);
			GymAppUtil.closeDB(connection);
		}

		return string;

	}

	@Override
	public List<Member> getAllMembers() throws GymAppDaoException {

		List<Member> list = new ArrayList<>();
		String query = "{call displayMembers()} ";
		Connection connection = GymAppUtil.getConnection();
		CallableStatement statement = null;
		ResultSet set = null;
		try {
			statement = connection.prepareCall(query);
			set = statement.executeQuery();
			while (set.next()) {
				Member member = new Member(set.getString(1), set.getInt(2), set.getString(3), set.getFloat(4));
				list.add(member);
			}

		} catch (SQLException e) {
			throw new GymAppDaoException("Dao:Data not present in the database");
		} finally {
			GymAppUtil.closingResources(set);
			GymAppUtil.closingResources(statement);
			GymAppUtil.closeDB(connection);
		}
		return list;

	}

	@Override
	public List<Member> listAllDetailsUsingage(int age) throws GymAppDaoException {

		List<Member> list = new ArrayList<>();
		String query = "select m.* ,g.* from gym g RIGHT JOIN member m on m.gymid=g.gymid where m.age>" + age;
		Connection connection = GymAppUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet set = null;
		try {
			statement = connection.prepareStatement(query);
			set = statement.executeQuery();
			while (set.next()) {
				Gym gym = new Gym(set.getInt(7), set.getString(8));
				Member member = new Member(set.getString(2), set.getInt(3), set.getString(4), set.getFloat(5), gym);
				list.add(member);
			}

		} catch (SQLException e) {
			throw new GymAppDaoException("Dao:Please give the age properly");
		} finally {

			GymAppUtil.closingResources(set);
			GymAppUtil.closingResources(statement);
			GymAppUtil.closeDB(connection);

		}
		return list;

	}

}
