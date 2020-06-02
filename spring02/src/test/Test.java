import com.xxc.beans.Account;
import com.xxc.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
  @org.junit.jupiter.api.Test
        public void testSaveAccount() {
            Account account = new Account();
            account.setName("黑马程序员");
            account.setMoney(100000f);
            ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
            IAccountService as = ac.getBean("accountService",IAccountService.class);
            as.saveAccount(account);
        }
    }

