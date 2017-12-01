package service;

import dao.AccountingDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class AccountingServiceTest {
    private int exitCode;
    private AccountingService accountingService;

    @Before
    public void setUp() throws Exception {
        AccountingDao accountingDao = Mockito.mock(AccountingDao.class);
        accountingService = new AccountingService(accountingDao);
    }

    //Тест считается успешным если все данные верны 0=0;
    @Test
    public void accountingSuccessTest() throws Exception {
        exitCode = accounting("2017-10-10", "2017-10-10", "100");
        assertEquals(0, exitCode);
    }

    //Тест считается успешным если дата не валидна 5=5
    @Test
    public void accountingInvalidDateTest() throws Exception {
        exitCode = accounting("2017-10-X", "2017-10-X", "100");
        assertEquals(5, exitCode);
    }

    //Тест считается успешным если обьем не валиден 5=5
    @Test
    public void accountingInvalidVolumeTest() throws Exception {
        exitCode = accounting("2017-10-10", "2017-10-10", "X");
        assertEquals(5, exitCode);
    }

    private int accounting(String dateStart, String dateEnd, String volume) throws DbException {
        exitCode = accountingService.accounting("Roman", dateStart, dateEnd, volume);
        return exitCode;
    }
}