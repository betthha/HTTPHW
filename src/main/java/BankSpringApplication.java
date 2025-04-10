import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = LiquibaseAutoConfiguration.class)
@EnableJpaRepositories
@EnableTransactionManagement
public class BankSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankSpringApplication.class, args);
    }
}