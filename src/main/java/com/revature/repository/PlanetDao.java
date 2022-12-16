package com.revature.repository;

import com.revature.models.User;
import com.revature.utilities.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;

import com.revature.models.Planet;

public class PlanetDao {

	public List<Planet> getAllPlanets() {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "select * from planets";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ArrayList<Planet> allPlanets = new ArrayList<>();
			while (rs.next()) {
				Planet currPlanet = new Planet();
				currPlanet.setId(rs.getInt("id"));
				currPlanet.setName(rs.getString("name"));
				currPlanet.setOwnerId(rs.getInt("ownerid"));
				allPlanets.add(currPlanet);
			}
			return allPlanets;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ArrayList<Planet> emptyList = new ArrayList<>();
			return emptyList;
		}
	}

	public Planet getPlanetByName(String owner, String planetName) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "select * from planets where name = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, planetName);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Planet planet = new Planet();
			planet.setId(rs.getInt("id"));
			planet.setName(rs.getString("name"));
			planet.setOwnerId(rs.getInt("ownerid"));
			return planet;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new Planet();
		}
	}

	public Planet getPlanetById(String username, int planetId) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "select * from planets where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, planetId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Planet planet = new Planet();
			planet.setId(rs.getInt("id"));
			planet.setName(rs.getString("name"));
			planet.setOwnerId(rs.getInt("ownerid"));
			return planet;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new Planet();
		}
	}

	public Planet createPlanet(String username, Planet p) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sqlStatement = "insert into planets values(default,?,?)";
			PreparedStatement ps = connection.prepareStatement(sqlStatement);
			ps.setString(1, p.getName());
			ps.setInt(2, p.getOwnerId());
			ps.execute();
			return p;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return new Planet();
		}
	}

	public void deletePlanetById(int planetId) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sqlStatement = "delete from planets where id=?";
			PreparedStatement ps = connection.prepareStatement(sqlStatement);
			ps.setInt(1, planetId);
			ps.execute();
			return;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return;
		}
	}
}
