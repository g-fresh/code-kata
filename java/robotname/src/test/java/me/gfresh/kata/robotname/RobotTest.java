package me.gfresh.kata.robotname;

import static java.util.stream.IntStream.range;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.*;

public class RobotTest {

	private Factory factory;

	@Before
	public void setUp() {
		factory = new Factory();
	}

	@Test
	public void hasName() {
		range(0, 1000).forEach(x -> {
			Robot robot = factory.makeRobot();
			assertTrue(isValid(robot.getName()));
		});
	}

	@Test
	public void differentRobotsHaveDifferentNames() {
		Robot robot = factory.makeRobot();
		range(0, 300000).forEach(x -> {
			assertNotEquals(robot.getName(), factory.makeRobot().getName());
		});
	}

	@Test
	public void resetName() {
		range(0, 150000).forEach(x -> {
			Robot robot = factory.makeRobot();
			final String originalName = robot.getName();
			factory.reset(robot);
			final String nameAfterReset = robot.getName();
			assertNotEquals(originalName, nameAfterReset);
			assertTrue(isValid(nameAfterReset));
		});
	}

	private static final String EXPECTED_ROBOT_NAME_PATTERN = "\\w{2}\\d{3}";

	private static boolean isValid(String name) {
		return name.matches(EXPECTED_ROBOT_NAME_PATTERN);
	}
}
