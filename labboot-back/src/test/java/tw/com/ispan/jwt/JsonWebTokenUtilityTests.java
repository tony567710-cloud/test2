package tw.com.ispan.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest 
public class JsonWebTokenUtilityTests {
    @Autowired 
    private JsonWebTokenUtility jsonWebTokenUtility;

    @Test
    public void test() {
        String data = "this is a demo";
        String token = jsonWebTokenUtility.createToken(data);
        System.out.println("token1="+token);

        String subject = jsonWebTokenUtility.validateToken(token);
        System.out.println("subject1="+subject);
    }

    @Test
    public void testEncrpt() {
        String data = "this is a demo";
        String token = jsonWebTokenUtility.createEncryptedToken(data);
        System.out.println("token2="+token);

        String subject = jsonWebTokenUtility.validateEncryptedToken(token);
        System.out.println("subject2="+subject);
    }
}
