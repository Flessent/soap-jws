package server;

import bank.BankServices;
import jakarta.xml.ws.Endpoint;
import logic.Account;
import logic.Customer;

import java.util.List;

public class ServerWS {
     public static void main(String[] args) throws Exception {

  //       Bearer bearer = new Bearer();

 //List<Account> accountList = bearer.listAccount();
//System.out.println( accountList.get(0).getCustomer().getAccounts());

String url="http://0.0.0.0:8080/";
         Endpoint.publish(url, new BankServices());
System.out.println("Bank Web Service deployed on "+url);
    }
}
