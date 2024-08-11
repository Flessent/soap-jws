package logic;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Data
@NoArgsConstructor
@XmlRootElement(name = "Account") // serialize this into a Java-Object named 'Account'
public class Account {

    private String code;
    private double amount = 0.0;
   // @XmlTransient // don't serialize this field into xml
    private Date creationDate;
   @ToString.Exclude
    private Customer customer;



    public void deleteAccount(String  accountCode) {

        this.customer.getAccounts().remove(this.customer.getAccounts().stream().filter(account -> account.getCode().equals(accountCode)));
    }

    public void addAccountToCustomer(Account account) {
        this.customer.getAccounts().add(account);

    }

    public Account getAccountInfos(String codeAccount) {

        List<Account> listAccount = this.customer.getAccounts().stream().filter(account -> account.getCode().equals(codeAccount))
                .collect(Collectors.toList());

        return listAccount.get(0);

    }

}
