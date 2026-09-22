package tw.com.ispan.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class CustomerBean {
    @Id
    @Column(name = "custid", length = 20)
    private String custid;

    @Column(name = "password")
    private byte[] password;

    @Column(name = "email", length = 30)
    private String email;

    @Column(name = "birth")
    private Date birth;
    
	@Override
	public String toString() {
		return "CustomerBean [custid=" + custid + ", email=" + email + ", birth=" + birth + "]";
	}
}
