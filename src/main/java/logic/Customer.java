package logic;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data

public class Customer {
private String customerCode;
private String name;
private String mail;
private List<Account> accounts;

public Customer(String customerCode, String name,String mail)
{
    this.customerCode=customerCode;
    this.name=name;
    this.mail=mail;
    this.accounts=new ArrayList<>();



}

public List<Account> getListAccounts(){
    return this.accounts;
}

public void addAccount(Account account) throws Exception {
    try {
         if (this.accounts==null){
             this.accounts= new ArrayList<>();
             System.err.println("NULL !!! ");
         }
        System.out.println("NOT NULL !!! "+account.getCode());
            this.accounts.add(account);


    } catch (Exception e)
    {
        System.err.println("Account exits already !!! ");

        throw  new Exception(e.getMessage());
    }
}


}
