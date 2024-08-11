package server;

import logic.Account;
import logic.Customer;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
public class Bearer {
    private List<Customer> listCustomers = new ArrayList<>() ;
    private List<Account> listAccounts= new ArrayList<>();


    public   List<Customer> listCustomers() throws Exception {

        this.listCustomers=Stream.of("flexe","merkel","modestine")
                .map(name ->
                        new Customer(name+"001", name,name+"@gov.de")
                ).collect(Collectors.toList());
        //System.out.println("Here Customer Code :" +this.listCustomers.get(0).getCustomerCode());
        return this.listCustomers;
    }


       public List<Account> listAccount() throws Exception {
            List<String> listCodeAccount= Stream.of("ACC001","ACC002","ACC003")
                    .collect(Collectors.toList());
               //System.out.println("Before Customer Code :" +listCustomers().get(0).getCustomerCode());
           this.listCustomers=listCustomers();
           for (int i = 0; i < listCodeAccount.size(); i++) {
               String code = listCodeAccount.get(i);
               Customer customer = this.listCustomers.get(i);
               //System.out.println("Customer Code :" +this.listCustomers.get(i).getCustomerCode());
               Account account = new Account(code, Math.random()*1000, new Date(), customer);
               //System.out.println("Account  Code :" +account.getCode());
               customer.addAccount(account);
               this.listAccounts.add(account);
           }

            return this.listAccounts;
        }


/*
public  List<Customer> assignCustomerToAccount() throws Exception {
        this.listCustomers=listCustomers();
        this.listAccounts=listAccount();
        System.out.println("Size Accounts List "+this.listAccounts.size()+"Size Customers List"+this.listCustomers.size());
    for (int i=0;i<this.listCustomers.size();i++){
        this.listCustomers.get(i).getAccounts().add(this.listAccounts.get(i));
        //this.listAccounts.get(i).setCustomer(this.listCustomers.get(i));
        //System.out.println("Code Account : "+ this.listCustomers.get(i).getCustomerCode());
    }
    return this.listCustomers;
}
*/




}

