//package test;
//
//import static org.junit.Assert.assertEquals;
//
//import java.io.OutputStream;
//import java.io.PrintStream;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import cli.CharacterStatusDisplay;
//import cli.GameStatusDisplay;
//import cli.heroStatusDisplay;
//import cli.ogreStatusDisplay;
//
//public class TestDisplay {
//
//	OutputStream outContent;
//
//	@Before
//	public void setUpStreams() {
//		outContent = new OutputStream();
//		System.setOut(new PrintStream(outContent));
//	}
//
//	@Test
//	public void testCharacterStatusDisplay() {
//		CharacterStatusDisplay d = new CharacterStatusDisplay();
//		//test wrongCommandInput()
//		d.wrongCommandInput(true);
//		assertEquals("Wrong input\n", outContent.toString());
//	}
//
//	@Test
//	public void testGameStatusDisplay() {
//		GameStatusDisplay d = new GameStatusDisplay();
//		//test guardAwoken()
//		d.guardAwoken(true);
//		assertEquals("If you step the guard like that, how do you expect to escape?", outContent.toString());
//		//test captured()
//		d.captured(true);
//		assertEquals("You got captured, better luck next time!", outContent.toString());
//		//test fatality()
//		d.fatality(true);
//		assertEquals("You got deadly hit, better luck next time!", outContent.toString());
//		//test nextLevel()
//		d.nextLevel(true);
//		assertEquals("Your challenge is not over...", outContent.toString());
//		//test gameWon()
//		d.gameWon(true);
//		assertEquals("Congratz, you did it!", outContent.toString());
//
//	}
//	
//	@Test
//	public void testHeroStatusDisplay() {
//		heroStatusDisplay d = new heroStatusDisplay();
//		//test wallColliding()
//		d.wallColliding(true);
//		assertEquals("Ouch, try not to faceplant next time!", outContent.toString());
//		//test keyColliding()
//		d.keyColliding(true);
//		assertEquals("Key aquired.", outContent.toString());
//		//test doorColliding()
//		d.doorColliding(true);
//		assertEquals("Exit opened, time to escape!", outContent.toString());
//		//test exitOpen()
//		d.exitOpen(true);
//		assertEquals("Exit opened, time to escape!", outContent.toString());
//
//	}
//	
//	@Test
//	public void testOgreStatusDisplay() {
//		ogreStatusDisplay d = new ogreStatusDisplay();
//		//test stunned()
//		d.stunned(1, true);
//		assertEquals("Ogre stunned for 1  more round.", outContent.toString());
//		d.stunned(2, true);
//		assertEquals("Ogre stunned for 1  more rounds.", outContent.toString());
//		//test justStunned()
//		d.justStunned(true);
//		assertEquals("Nice hit! You just stunned an Ogre!", outContent.toString());
//
//	}
//
//}
