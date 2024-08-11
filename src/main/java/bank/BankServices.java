package bank;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import logic.Account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import server.Bearer;

// POJO
@WebService(serviceName = "BankWS")
public class BankServices {
    Bearer bearer = new Bearer();
    Operation operation = new Operation();

    @WebMethod(operationName = "EuroToDollar")
    public String conversion(@WebParam(name = "accountCode") String accountCode,@WebParam(name ="exchange" ) String exchange) throws Exception {


        List<String> listCodes= new ArrayList<>();
        bearer.listAccount().forEach(acc ->listCodes.add(acc.getCode())  );


        if(!listCodes.contains(accountCode) || accountCode.isEmpty()){
            throw  new Exception("Account does not exist or is not provided!!!");
        }
        Account account = bearer.listAccount().stream().filter(acc -> acc.getCode().equals(accountCode)).collect(Collectors.toList()).get(0);
        switch (exchange){
            case "dollar":

                return  "Your current balance is "+account.getAmount()+ "and it is equivalent to"+account.getAmount()*0.65;

            case "euro":

                return  "Your current balance is "+account.getAmount()+ "and it is equivalent to"+account.getAmount()*0.55;
        }

       return "Exchange not supported by our bank";

    }

    @WebMethod(operationName = "getAccountInfos")
    public String getAccount(@WebParam(name = "code") String accountCode) throws Exception {
        List<String> listCodes= new ArrayList<>();
        bearer.listAccount().forEach(acc ->listCodes.add(acc.getCode())  );


        if(!listCodes.contains(accountCode)){
            throw  new Exception("Account does not exist !!!");
        }


            Account account = bearer.listAccount().stream().filter(acc -> acc.getCode().equals(accountCode)).collect(Collectors.toList()).get(0);
            return "Code Account : "+ account.getCode()+ "\n" +
                " Balance :"+account.getAmount()+ "\n" + "Date Creation : "+account.getCreationDate()+ "\n"+  " Customer Name : " +account.getCustomer().getName();


    }

    @WebMethod(operationName = "getAllAccounts")
    public List<String> listAccount() throws Exception {
        List<String> allAccountInfos = new ArrayList<>();
        List<Account> accounts = new ArrayList<>(bearer.listAccount());

        for (Account account : accounts) {
            String accountInfo = getAccount(account.getCode());
            allAccountInfos.add(accountInfo);
        }

        return allAccountInfos;
    }



    @WebMethod(operationName = "transferMoney")
    public void transferMonaey(@WebParam(name = "accountCodeFrom") String accountCodeFrom, @WebParam(name = "accountCodeTo") String accountCodeTo,
                               @WebParam(name = "amount") double amount) throws Exception {
        List<String> listCodes= new ArrayList<>();
        bearer.listAccount().forEach(acc ->listCodes.add(acc.getCode())  );


        if(!listCodes.contains(accountCodeFrom) || !listCodes.contains(accountCodeTo)){
            throw  new Exception("Account does not exist !!!");
        }

        Account accountFrom = bearer.listAccount().stream().filter(acc -> acc.getCode().equals(accountCodeFrom)).collect(Collectors.toList()).get(0);
        Account accountTo = bearer.listAccount().stream().filter(acc -> acc.getCode().equals(accountCodeTo)).collect(Collectors.toList()).get(0);
        accountFrom.setAmount(50);
        accountTo.setAmount(10);
        System.out.println("Before : "+accountFrom.getAmount()+ "and" + accountTo.getAmount());
        operation.transfer(accountFrom, accountTo, amount);
        System.out.println("After : "+accountFrom.getAmount()+ "and" + accountTo.getAmount());


    }

    @WebMethod(operationName = "removeMoney")
    public void removeMonaey(@WebParam(name = "accountCodeFrom") String accountCode, @WebParam(name = "amount") double amount) throws Exception {
        List<String> listCodes= new ArrayList<>();
        bearer.listAccount().forEach(acc ->listCodes.add(acc.getCode())  );


        if(!listCodes.contains(accountCode)){
            throw  new Exception("Account does not exist !!!");
        }
        Account account= bearer.listAccount().stream().filter(acc ->acc.getCode().equals(accountCode) ).collect(Collectors.toList()).get(0);
        operation.removeMoney(account,amount);
    }



}
