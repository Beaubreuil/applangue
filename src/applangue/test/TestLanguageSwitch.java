package applangue.test;
import static org.junit.Assert.*;
import org.junit.Test;
import applangue.FenetreSaisie;
import java.util.Locale;
import java.util.ResourceBundle;

public class TestLanguageSwitch {

	@Test
	public void TestAll() {
		TestSwitch();
	}
	
	private void TestSwitch() {
		Locale locale = new Locale("en","");
        ResourceBundle messages = ResourceBundle.getBundle("applangue.test.lang.messages", locale);
        assertEquals(messages.getString("hello"),"Hello");
        locale = new Locale("fr","");
        messages= ResourceBundle.getBundle("applangue.test.lang.messages", locale);
        assertEquals(messages.getString("hello"),"Bonjour");
        locale = new Locale("es","");
        messages= ResourceBundle.getBundle("applangue.test.lang.messages", locale);
        assertEquals(messages.getString("hello"),"Holla");
	}
}
