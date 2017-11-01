package com.jys.maze.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class IRConnectorTest {
	IRConnector testIRConnector;
	String response;
	JSONObject obj;

	@Before
	public void init() {
		testIRConnector = new IRConnector();
		response = testIRConnector.start();
		obj = new JSONObject(response);
	}

	@Test
	public void testStart() {
		System.out.println("Start(): " + response);
		Set<String> result = obj.keySet();

		assertTrue(!response.contains("ERROR"));

		assertTrue(result.contains("abilities"));
		assertTrue(result.contains("yStartingPosition"));
		assertTrue(result.contains("startingDirection"));
		assertTrue(result.contains("baseUrl"));
		assertTrue(result.contains("controller"));
		assertTrue(result.contains("persona"));
		assertTrue(result.contains("xStartingPosition"));
		assertTrue(result.contains("id"));
		assertTrue(result.contains("quickFeet"));
		assertTrue(result.contains("xPos"));
		assertTrue(result.contains("yPos"));
		assertTrue(result.contains("isAtExit"));
	}

	@Test
	public void testAdvance() {
		// should be able to advance even though invalid. except the positon might not
		// change
		String advanceResponse = testIRConnector.advance(obj.getString("id"), false);
		System.out.println("Advance(): " + advanceResponse);

		obj = new JSONObject(advanceResponse);
		Set<String> result = obj.keySet();

		assertTrue(result.contains("nTurns"));
		assertTrue(result.contains("nAdvances"));
		assertTrue(result.contains("surroundings"));
		assertEquals(1, obj.get("nAdvances"));
	}

	@Test
	public void testCheck() {
		String checkResponse = testIRConnector.check(obj.getString("id"), obj.getInt("xStartingPosition"),
				obj.getInt("yStartingPosition"));

		System.out.println("Check(): " + checkResponse);
		JSONObject testObj = new JSONObject(checkResponse);

		assertTrue(testObj.getBoolean("isAllowed"));

		checkResponse = testIRConnector.check(obj.getString("id"), obj.getInt("xStartingPosition") - 1,
				obj.getInt("yStartingPosition"));
		assertTrue(checkResponse.contains("Error"));
	}

	@Test
	public void testTurn() {
		String tmpId = obj.getString("id");
		String turnResponse = testIRConnector.turn(tmpId, "left");
		System.out.println("Turn(): " + turnResponse);

		obj = new JSONObject(turnResponse);
		Set<String> result = obj.keySet();

		assertEquals(1, obj.getInt("nTurns"));
		assertTrue(result.contains("nTurns"));
		assertTrue(result.contains("nAdvances"));
		assertTrue(result.contains("surroundings"));

		testIRConnector.turn(tmpId, "left");
		testIRConnector.turn(tmpId, "left");
		testIRConnector.turn(tmpId, "left");
		turnResponse = testIRConnector.turn(tmpId, "left");
		obj = new JSONObject(turnResponse);

		assertEquals(5, obj.getInt("nTurns"));

	}

}
