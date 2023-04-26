package com.mindtree.entity;

import java.io.Serializable;

public class Member implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	private String gender;
	private float height;
	private Gym gym;
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Member(String name, int age, String gender, float height) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.height = height;
	}


	public Member(String name, int age, String gender, float height, Gym gym) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.height = height;
		this.gym = gym;
	}


	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public Gym getGym() {
		return gym;
	}
	public void setGym(Gym gym) {
		this.gym = gym;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((gym == null) ? 0 : gym.hashCode());
		result = prime * result + Float.floatToIntBits(height);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (age != other.age)
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (gym == null) {
			if (other.gym != null)
				return false;
		} else if (!gym.equals(other.gym))
			return false;
		if (Float.floatToIntBits(height) != Float.floatToIntBits(other.height))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + ", gender=" + gender + ", height=" + height + ", gym=" + gym
				+ "]";
	}
	
	
	

	
}
