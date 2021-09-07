package se.nackademin.java20.lab1.application;

import se.nackademin.java20.lab1.domain.Account;
import se.nackademin.java20.lab1.domain.AccountRepository;

import javax.transaction.Transactional;

public class PersonalFinanceService {
    private final AccountRepository accountRepository;

    public PersonalFinanceService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Account openAccount(String holder) {
        return accountRepository.save(new Account(holder, 0));
    }

    @Transactional
    public Account withdraw(long id, String holder, long amount) {
        Account account = accountRepository.findByUserIdAndAccountId(holder, id).orElseThrow(() -> new RuntimeException("Could not find account"));
        account.withdraw(amount);
        accountRepository.save(account);
        return account;
    }

    @Transactional
    public Account deposit(long id, String holder, long amount) {
        Account account = accountRepository.findByUserIdAndAccountId(holder, id).orElseThrow(() -> new RuntimeException("Could not find account"));
        account.deposit(amount);
        accountRepository.save(account);
        return account;
    }

    public Account findAccount(String userId, Long accountId) {
        return accountRepository.findByUserIdAndAccountId(userId, accountId).orElseThrow(() -> new RuntimeException("Could not find account"));
    }
}
