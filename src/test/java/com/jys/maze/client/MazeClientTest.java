package com.jys.maze.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jys.maze.builder.MazeBuilder;
import com.jys.maze.connector.IRConnector;
import com.jys.maze.model.Maze;

@RunWith(MockitoJUnitRunner.class)
public class MazeClientTest {

	@Mock
	Maze mockMaze;

	@Mock
	IRConnector mockIRConnector;

	@Mock
	MazeClient mockMazeClient;

	@Mock
	MazeBuilder mockMazeBuilder;

	@Before
	public void init() {

	}

	@Test
	public void testStart() {

		// mock checkAutoSolve() = both true false
		// mock welcome() = both true false
		// mock run() = do nothing
		// mock takeInput() = both "y" and "n"
		//
		// when(mockMaze.getPersona()).thenReturn("BruceLee");
		// mockMazeClient.setMaze(mockMaze);
		// when(mockMazeClient.start()).thenCallRealMethod();
		// when(mockMazeClient.welcome()).thenReturn(false);
		//
		// boolean result = mockMazeClient.start();
		// assertTrue(!result);
		//
		// when(mockMazeClient.welcome()).thenReturn(true);
		// when(mockMazeClient.takeInput()).thenReturn("y");
		//
		// String test = mockMazeClient.takeInput();
		// boolean testI = mockMazeClient.welcome();
		//
		// result = mockMazeClient.start();
		//
		// assertTrue(result);

	}

	@Test
	public void testRun() {
		MazeClient testClient = new MazeClient();
		testClient.setConnector(mockIRConnector);
		testClient.setMaze(mockMaze);

//		when(mockMaze.getxStartingPosition()).thenReturn(1);
//		when(mockMaze.getxExitPosition()).thenReturn(2);
//		when(mockMaze.getyStartingPosition()).thenReturn(1);
//		when(mockMaze.getyExitPosition()).thenReturn(2);

		System.out.println(mockMaze.getxStartingPosition());

		// is there something that calls something when called?
//		when(mockMaze.printGrid()).thenCalls(mockMaze.setxExitPosition(1));

		testClient.run(mockMaze);

		verify(mockMaze, times(1)).updateTile(any(int.class), any(int.class), any(String.class));
		verify(mockMaze, times(1)).printGrid();

	}

	@Test
	public void testAdvance() {
		MazeClient testClient = new MazeClient();
		testClient.setConnector(mockIRConnector);
		testClient.setMaze(mockMaze);

		doReturn("north").when(mockMaze).getStartingDirection();
		doReturn("true").when(mockIRConnector).check(any(String.class), any(int.class), any(int.class));
		doNothing().when(mockMaze).updateTile(any(int.class), any(int.class), any(String.class));

		testClient.advance(false);

		verify(mockMaze, times(2)).updateTile(any(int.class), any(int.class), any(String.class));

	}

	@Test
	public void testCheckQF() {
		MazeClient testClient = new MazeClient();

		testClient.setConnector(mockIRConnector);
		testClient.setMaze(mockMaze);

		doReturn("north").when(mockMaze).getStartingDirection();
		doReturn("iamquicktrueright").when(mockIRConnector).check(any(String.class), any(int.class), any(int.class));

		boolean result = testClient.checkQF();

		assertTrue(result);

	}

	@Test
	public void testCheck() {
		MazeClient testClient = new MazeClient();

		testClient.setConnector(mockIRConnector);
		testClient.setMaze(mockMaze);

		doReturn("north").when(mockMaze).getStartingDirection();
		doReturn("iamlegendtrueright").when(mockIRConnector).check(any(String.class), any(int.class), any(int.class));

		boolean result = testClient.check();

		assertTrue(result);

	}

	@Test
	public void testTurn() {
		MazeClient testClient = new MazeClient();

		testClient.setConnector(mockIRConnector);
		testClient.setMaze(mockMaze);

		doReturn("random").when(mockIRConnector).turn(any(String.class), any(String.class));
		doReturn("randomValue").when(mockMaze).getId();
		doReturn("north").when(mockMaze).getStartingDirection();
		doNothing().when(mockMaze).setStartingDirection(any(String.class));

		when(mockMazeClient.turn(any(String.class))).thenCallRealMethod();

		String resultI = testClient.turn("l");
		String resultII = testClient.turn("r");

		assertEquals("west", resultI);
		assertEquals("east", resultII);

	}

	@Test
	public void testCheckAutoSolveCaller() {

		// mock autoSolveHERO() - verify if it gets called
		when(mockMazeClient.takeInput()).thenReturn("y");
		when(mockMazeClient.checkAutoSolve(any(String.class))).thenCallRealMethod();

		boolean result = mockMazeClient.checkAutoSolve("BruceLee");
		verify(mockMazeClient, times(0)).autoSolveHuman();
		assertTrue(!result);

		mockMazeClient.checkAutoSolve("Human");
		verify(mockMazeClient, times(1)).autoSolveHuman();

	}

	@Test
	public void testWelcome() {
		when(mockMazeClient.takeInput()).thenReturn("y");
		when(mockMazeClient.welcome()).thenCallRealMethod();

		boolean result = mockMazeClient.welcome();

		assertTrue(result);
	}

}
