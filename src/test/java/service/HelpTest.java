package service;

import org.junit.Before;
import org.junit.Test;

public class HelpTest {
    private ParseService cmd;

    @Before
    public void setUp() throws Exception {
        cmd = new ParseService();
    }

    //Тест считается успешным если вызвана справка
    @Test
    public void helpArgument() {
        cmd.printHelp();
    }
}
