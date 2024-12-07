package backend;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScoreManagerTest {
	
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@Test
	void testConstructorInititalization() {
		ScoreManager scoreMan = new ScoreManager();
		assertEquals(scoreMan.getScore(), 0);
		assertEquals(scoreMan.getHighScore(), 0);
	}
	
	@Test
	void testAddScoreOnce() {
		ScoreManager scoreMan = new ScoreManager();
		scoreMan.addScore(5);
		assertEquals(scoreMan.getScore(), 5);
	}
	
	@Test
	void testAddScoreMultiple() {
		ScoreManager scoreMan = new ScoreManager();
		scoreMan.addScore(5);
		scoreMan.addScore(10);
		scoreMan.addScore(25);
		scoreMan.addScore(10);
		assertEquals(scoreMan.getScore(), 50);
	}
	
	@Test
	void testResetScore() {
		ScoreManager scoreMan = new ScoreManager();
		scoreMan.addScore(5);
		assertEquals(scoreMan.getScore(), 5);
		scoreMan.resetScore();
		assertEquals(scoreMan.getScore(), 0);
	}
	
	@Test
	void testGetHighScore() {
		ScoreManager scoreMan = new ScoreManager();
		scoreMan.addScore(100);
		scoreMan.addScore(20);
		assertEquals(scoreMan.getHighScore(), 120);
		scoreMan.resetScore();
		scoreMan.addScore(50);
		assertEquals(scoreMan.getHighScore(), 120);
		scoreMan.addScore(150);
		assertEquals(scoreMan.getHighScore(), 200);
	}
	
	@Test
	void testDisplayScore() {
		ScoreManager scoreMan = new ScoreManager();
		scoreMan.addScore(100);
		scoreMan.addScore(20);
		scoreMan.resetScore();
		scoreMan.addScore(50);
		scoreMan.displayScore();
		assertEquals(outputStreamCaptor.toString().trim(), "Current Score: 50\r\n"
				+ "High Score: 120");
	}

}
