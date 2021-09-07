package se.nackademin.java20.lab1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import se.nackademin.java20.lab1.application.PersonalFinanceService;
import se.nackademin.java20.lab1.domain.AccountRepository;
import se.nackademin.java20.lab1.persistance.AccountRepositoryHibernate;
import se.nackademin.java20.lab1.risk.RestRiskAssessment;
import se.nackademin.java20.lab1.risk.RiskAssessmentAdapter;

import javax.persistence.EntityManager;

@Configuration
public class ApplicationConfiguration {
    @Value("${RISK_URL:https://localhost:8081}")
    private String riskServiceBaseUrl;

    @Bean
    public AccountRepository accountRepository(EntityManager em) {
        return new AccountRepositoryHibernate(em);
    }

    @Bean
    public PersonalFinanceService personalFinanceService(AccountRepository accountRepository) {
        return new PersonalFinanceService(accountRepository);
    }
    @Bean
    public RiskAssessmentAdapter riskAssesmentAdapter(){
        return new RestRiskAssessment( new RestTemplate(), riskServiceBaseUrl);
    }
}
