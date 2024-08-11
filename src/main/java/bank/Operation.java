package bank;

import logic.Account;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Operation {
    public void transfer(Account fromAccount,Account toAccount,double amount) throws Exception {
        if(fromAccount.getAmount() < amount || fromAccount.getAmount()<0.0){
            throw  new Exception("insufficient or negative  balance !!!");
        }

        toAccount.setAmount(toAccount.getAmount()+amount);
        fromAccount.setAmount(fromAccount.getAmount()-amount);

    }

    public  Account removeMoney(Account account,double amount) throws Exception {
        if(account.getAmount() < amount || account.getAmount()<0.0){
            throw  new Exception("insufficient or negative  balance !!!");
        }
        account.setAmount(account.getAmount()-amount);

        return account;
    }

    public  Account addMoney(Account account,double amount) throws Exception {

        account.setAmount(account.getAmount()+amount);

        return account;
    }





}
