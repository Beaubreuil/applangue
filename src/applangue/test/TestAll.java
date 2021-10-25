package applangue.test;
import static org.junit.Assert.*;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import applangue.FenetreSaisie;
import applangue.test.TestAction1;
import applangue.test.TestAction2;
import applangue.test.TestAction3;
import applangue.test.TestMot;

public class TestAll {
	
	@Test
	public void TestEverything() {
		TestAction1 t1 = new TestAction1();
		t1.TestAll();
		TestAction2 t2 = new TestAction2();
		t2.TestAll();
		TestAction3 t3 = new TestAction3();
		t3.TestAll();
		TestMot t4 = new TestMot();
		t4.TestAll();
	}
}
