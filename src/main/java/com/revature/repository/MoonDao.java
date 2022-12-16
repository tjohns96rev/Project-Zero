package com.revature.repository;

import com.revature.models.User;
import com.revature.utilities.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;

import com.revature.models.Moon;
import com.revature.models.Planet;

public class MoonDao {

	public List<Moon> getAllMoons() {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "select * from moons";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ArrayList<Moon> allMoons = new ArrayList<>();
			while (rs.next()) {
				Moon currMoon = new Moon();
				currMoon.setId(rs.getInt("id"));
				currMoon.setName(rs.getString("name"));
				currMoon.setMyPlanetId(rs.getInt("ownerid"));
				allMoons.add(currMoon);
			}
			return allMoons;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ArrayList<Moon> emptyList = new ArrayList<>();
			return emptyList;
		}
	}

	public Moon getMoonByName(String username, String moonName) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "select * from moons where name = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, moonName);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Moon moon = new Moon();
			moon.setId(rs.getInt("id"));
			moon.setName(rs.getString("name"));
			moon.setMyPlanetId(rs.getInt("ownerid"));
			return moon;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new Moon();
		}
	}

	public Moon getMoonById(String username, int moonId) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "select * from moons where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, moonId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Moon moon = new Moon();
			moon.setId(rs.getInt("id"));
			moon.setName(rs.getString("name"));
			moon.setMyPlanetId(rs.getInt("ownerid"));
			return moon;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new Moon();
		}
	}

	public Moon createMoon(String username, Moon m) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sqlStatement = "insert into moons values(default,?,?)";
			PreparedStatement ps = connection.prepareStatement(sqlStatement);
			ps.setString(1, m.getName());
			ps.setInt(2, m.getMyPlanetId());
			ps.execute();
			return m;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return new Moon();
		}
	}

	public void deleteMoonById(int moonId) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sqlStatement = "delete from moons where id=?";
			PreparedStatement ps = connection.prepareStatement(sqlStatement);
			ps.setInt(1, moonId);
			ps.execute();
			return;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return;
		}
	}

	public List<Moon> getMoonsFromPlanet(int planetId) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "select * from moons where ownerid = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, planetId);
			ResultSet rs = ps.executeQuery();
			ArrayList<Moon> moons = new ArrayList<>();
			while (rs.next()) {
				Moon currMoon = new Moon();
				currMoon.setId(rs.getInt("id"));
				currMoon.setName(rs.getString("name"));
				currMoon.setMyPlanetId(rs.getInt("ownerid"));
				moons.add(currMoon);
			}
			return moons;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ArrayList<Moon> moons = new ArrayList<>();
			return moons;
		}
	}
}
