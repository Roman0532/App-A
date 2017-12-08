package service;

import dao.AuthorizationDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthorizationServiceTest {
    private AuthorizationService authorizationService;
    private AuthorizationDao authorizationDao;
    private int exitCode;

    @Before
    public void setUp() throws Exception {
        authorizationDao = Mockito.mock(AuthorizationDao.class);
        authorizationService = new AuthorizationService(authorizationDao);

        when(authorizationDao.isFindRes("Roman", "xxx", "READ")).thenReturn(null);
        when(authorizationDao.isFindRes("Roman1", "a.b", "EXECUTE")).thenReturn(null);
        when(authorizationDao.isFindRes("Roman", "a.b", "READ")).thenReturn("a.b");
    }

    //Тест считается успешным если все данные верны 0=0
    @Test
    public void authorizeSuccessTest() throws Exception {
        exitCode = authorizationService.authorize("Roman", "READ", "a.b");
        assertEquals(0, exitCode);
        verify(authorizationDao).isFindRes("Roman", "a.b", "READ");
    }

    //Тест считается успешным если пользователь с правами EXECUTE не имеет доступ к ресурсу a.b 4=4
    @Test
    public void authorizeNoAccess() throws Exception {
        exitCode = authorizationService.authorize("Roman1", "EXECUTE", "a.b");
        assertEquals(4, exitCode);
        verify(authorizationDao).isFindRes("Roman1", "a.b", "EXECUTE");
    }

    //Тест считается успешным если пользователь не имеет доступ к ресурсу xxx 4=4
    @Test
    public void authorizeNotFoundResource() throws Exception {
        exitCode = authorizationService.authorize("Roman", "READ", "xxx");
        assertEquals(4, exitCode);
        verify(authorizationDao).isFindRes("Roman", "xxx", "READ");
    }

    //Тест считается успешным если роль не валидна 3=3
    @Test
    public void authorizeNotFoundRole() throws Exception {
        exitCode = authorizationService.authorize("Roman", "xxx", "a.b");
        assertEquals(3, exitCode);
    }
}