/**
 * 
 */
package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ubu.gii.dass.c01.DuplicatedInstanceException;
import ubu.gii.dass.c01.NotFreeInstanceException;
import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.ReusablePool;

/**
 * @author Alberto Uriarte Martinez
 *
 */
public class ReusablePoolTest {
	
	private ReusablePool pool;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		pool = ReusablePool.getInstance();

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		pool = null;

	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		ReusablePool testPool = ReusablePool.getInstance();
		
		assertTrue("Comprobamos que la primera instancia no sea null", pool != null);
		assertTrue("Comprobamos que la segunda instancia no sea null", testPool != null);
		assertEquals("Comprobamos que las instancias son iguales",pool, testPool); 
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 * @throws NotFreeInstanceException 
	 */
	@Test
	public void testAcquireReusable() throws NotFreeInstanceException {
		ReusablePool testPool = ReusablePool.getInstance();

		Reusable reusableObject;

		reusableObject = testPool.acquireReusable();
		assertTrue("Comprobamos que el elemento adquirido no es null",reusableObject != null);
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 * @throws NotFreeInstanceException 
	 * @throws DuplicatedInstanceException 
	 */
	@Test
	public void testReleaseReusable() throws NotFreeInstanceException, DuplicatedInstanceException  {
		Reusable testPool1 = pool.acquireReusable();
		Reusable testPool2 = pool.acquireReusable();

		pool.releaseReusable(testPool1);	
		pool.releaseReusable(testPool2);	

		assertEquals("Comprobamos que la ultima instancia creada es la que sera adquirida",testPool2, pool.acquireReusable());

		assertNotEquals("Comprobamos que las instancias no son iguales", testPool1, testPool2);

	}

}
