import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LibTest {
	
	public static Lib test = new Lib();
	
	@Test
	void testGetw() {
		assertEquals(1.0, Lib.getw('+'));
		assertEquals(1.0, Lib.getw('-'));
		assertEquals(2.0, Lib.getw('*'));
		assertEquals(2.0, Lib.getw('/'));
	}
	
	@Test
	void testCalced() {
		assertEquals(3,   Lib.calced(1,  2,  '+'));
		assertEquals(9,   Lib.calced(3,  6,  '+'));
		assertEquals(12,  Lib.calced(2,  6,  '*'));
		assertEquals(13,  Lib.calced(15, 2,  '-'));
		assertEquals(4.2, Lib.calced(21, 5,  '/'));
		assertEquals(34,  Lib.calced(2,  17, '*'));
	}
	
	@Test
	void testCalc() {
//		assertEquals(5, Lib.calc("5"));
//		assertEquals(-5, Lib.calc("-5"));
//		assertEquals(-15, Lib.calc("-5*3"));
//		assertEquals(77, Lib.calc("(34-29)+52*1+10*2"));
		assertEquals(47, Lib.calc("3+5*(2+4*2)-6"));
//		assertEquals(6, Lib.calc("1+2*3-4+9/3"));
//		assertEquals(4, Lib.calc("(2+4-3)/3+5-2"));
	}
}
