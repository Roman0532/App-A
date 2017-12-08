package service;

import dao.AuthenticationDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {
    private AuthenticationService authenticationService;
    private AuthenticationDao authenticationDao;
    private int exitCode;

    @Before
    public void setUp() throws Exception {
        authenticationDao = Mockito.mock(AuthenticationDao.class);
        PasswordService passwordService = new PasswordService();
        authenticationService = new AuthenticationService(authenticationDao, passwordService);

        when(authenticationDao.findPassword("Roman")).thenReturn("6b1535f97c2a20f93c899060586b6287");
        when(authenticationDao.findSalt("Roman")).thenReturn("e3362c472c18185098f69dedd661cabebfe982e6138863417d2f2672be8976aa");
    }

    @Test
    public void authenticateSuccessTest() throws Exception {
        when(authenticationDao.findLogin("Roman")).thenReturn("Roman");

        exitCode = authenticationService.authenticate("Roman", "123");
        assertEquals(0, exitCode);
        verify(authenticationDao).findLogin("Roman");
        verify(authenticationDao).findPassword("Roman");
        verify(authenticationDao).findSalt("Roman");
    }

    @Test
    public void authenticateInvalidLoginTest() throws Exception {
        when(authenticationDao.findLogin("Roman123")).thenReturn(null);

        exitCode = authenticationService.authenticate("Roman123", "123");
        assertEquals(1, exitCode);
        verify(authenticationDao).findLogin("Roman123");
    }

    @Test
    public void authenticateHelpTest() throws Exception {
        when(authenticationDao.findLogin("Roman")).thenReturn("Roman");

        exitCode = authenticationService.authenticate("Roman", "1231");
        assertEquals(2, exitCode);
        verify(authenticationDao).findLogin("Roman");
        verify(authenticationDao).findPassword("Roman");
        verify(authenticationDao).findSalt("Roman");
    }
}