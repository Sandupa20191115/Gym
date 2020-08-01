package sample;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class MyGymManagerTest {

    @org.junit.Test
    public void start() {
    }

    @org.junit.Test
    public void validatingStrings() {

        //since this is a test the system.in getting input from the keyboard is overwritten to a teh string I expect
        ByteArrayInputStream byteArrayIs = new ByteArrayInputStream("Hello".getBytes());
        System.setIn(byteArrayIs);

        assertEquals("Hello",MyGymManager.validatingStrings("Enter Hello :"));

    }

    @org.junit.Test
    public void validatingInts() {
        ByteArrayInputStream byteArrayIs = new ByteArrayInputStream("5".getBytes());
        System.setIn(byteArrayIs);

        assertEquals("5",MyGymManager.validatingInts("Enter five :"));
    }

}