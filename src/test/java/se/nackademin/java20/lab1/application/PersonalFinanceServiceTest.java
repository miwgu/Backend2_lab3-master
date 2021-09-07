package se.nackademin.java20.lab1.application;

import org.junit.jupiter.api.Test;
import se.nackademin.java20.lab1.domain.Account;
import se.nackademin.java20.lab1.domain.AccountRepository;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class PersonalFinanceServiceTest {

    static class Hej{
        String name(){
            return "Dan";
        }
    }
    @Test
    void name(){
        //Hej hej =mock(Hej.class);
        Hej hej= new Hej();//Dan
        //when(hej.name()).thenReturn("Kalle");// always retur Kalle

        //assertEquals("Kalle",hej.name());
        assertEquals("Dan", hej.name());
    }

    static class Sample{
        String method1(int i){
            return "1";
        }
    }
    @Test
    void method1Test(){
        Sample sample = mock(Sample.class);
        sample.method1(10);
        sample.method1(20);
        sample.method1(30);
        sample.method1(30);

        verify(sample,times(1)).method1(10);
        verify(sample,times(1)).method1(20);
        verify(sample,times(2)).method1(30);
        verify(sample,times(4)).method1(anyInt());

        verify(sample,atLeastOnce()).method1(10);
        verify(sample,atLeast(2)).method1(30);
        verify(sample,atMost(2)).method1(30);
        verify(sample,never()).method1(0);

    }


    @Test
    void canOpenAccountAndDeposit(){
        AccountRepository accountRepository=mock(AccountRepository.class);
        when(accountRepository.findByUserIdAndAccountId("Dan",1)).thenReturn(of(new Account("Dan",0)));
        when(accountRepository.findByUserIdAndAccountId("Mi",2)).thenReturn(of(new Account("Mi",0)));
        PersonalFinanceService personalFinanceService= new PersonalFinanceService(accountRepository);
        //Account account = personalFinanceService.openAccount("Dan");
        Account account2 = personalFinanceService.deposit(1,"Dan",100);
        Account account3 =personalFinanceService.deposit(2,"Mi",1000);

        assertEquals(account2.getId(),0); //Why the accountid is always 0???
        assertEquals(account2.getHolder(),"Dan");
        assertEquals(account2.getBalance(),100);
        assertEquals(account3.getId(),0);
        assertEquals(account3.getHolder(),"Mi");
        assertEquals(account3.getBalance(),1000);
        //assertNotEquals(account2.getId(),1);
        //assertNotEquals(account2.getHolder(),"Don");
        //assertNotEquals(account2.getBalance(),0);
        //System.out.println(account.getId());
        System.out.println(account2.getId());
        System.out.println(account2.getHolder());
        System.out.println(account2.getBalance());
        System.out.println(account3.getId());
        System.out.println(account3.getHolder());
        System.out.println(account3.getBalance());

        verify(accountRepository,times(2)).save(any());

    }


    @Test
    void canDeposit(){
        AccountRepository accountRepository=mock(AccountRepository.class);

        when(accountRepository.findByUserIdAndAccountId("Dan",1)).thenReturn(of(new Account("Dan",0)));
        PersonalFinanceService personalFinanceService= new PersonalFinanceService(accountRepository);
        Account account= personalFinanceService.deposit(1,"Dan",100);

        //assertEquals(account.getId(),0); why expected id is 0???
        assertEquals(account.getHolder(),"Dan");
        assertEquals(account.getBalance(),100);
        assertNotEquals(account.getBalance(),101);

        verify(accountRepository,times(1)).save(any());// times:how many times the method is called
    }

    @Test
    void canDepositAndWithdraw(){
        AccountRepository accountRepository=mock(AccountRepository.class);

        when(accountRepository.findByUserIdAndAccountId("Dan",1)).thenReturn(of(new Account("Dan",0)));
        PersonalFinanceService personalFinanceService= new PersonalFinanceService(accountRepository);

        Account account= personalFinanceService.deposit(1,"Dan",1000);
        Account account2= personalFinanceService.withdraw(1,"Dan",100);

        assertEquals(account.getBalance(),900);
        assertNotEquals(account.getBalance(),1000);
        verify(accountRepository,times(2)).save(any());
    }

    @Test
    void canNotWithdraw(){
        AccountRepository accountRepository=mock(AccountRepository.class);

        when(accountRepository.findByUserIdAndAccountId("Dan",1)).thenReturn(of(new Account("Dan",0)));
        PersonalFinanceService personalFinanceService= new PersonalFinanceService(accountRepository);

        //Account account= personalFinanceService.withdraw(1,"Dan",100);
        assertThrows(IllegalStateException.class, () -> personalFinanceService.withdraw(1,"Dan",100));

        //assertEquals(account.getBalance(),0);
        //assertNotEquals(account.getBalance(),-100);
        verify(accountRepository,times(0)).save(any());
    }

}
