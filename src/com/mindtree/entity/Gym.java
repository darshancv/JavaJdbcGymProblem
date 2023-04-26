package com.mindtree.entity;

import java.io.Serializable;

public class Gym implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int gymId;
	private String name;

	public Gym() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Gym(int gymId, String name) {
		super();
		this.gymId = gymId;
		this.name = name;
	}

	public int getgymId() {
		return gymId;
	}

	public void setGymid(int gymid) {
		this.gymId = gymid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Gym [gymId=" + gymId + ", name=" + name + "]";
	}

	

	

	

}
