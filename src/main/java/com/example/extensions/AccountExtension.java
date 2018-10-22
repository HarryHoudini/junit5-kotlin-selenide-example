package com.example.extensions;


import org.junit.jupiter.api.extension.*;

public class AccountExtension implements BeforeAllCallback, AfterAllCallback {

    public String username;
    public String email;
    public String password;


    private AccountExtension(AccountBuilder adminBuilder){
        this.email = adminBuilder.email;
        this.username = adminBuilder.username;
        this.password = adminBuilder.password;
    }



    public void printEmail() {
        System.out.println("My Email is "+ email);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public String getEmail(){
        return this.email;
    }

    public String getName(){
        return this.username;
    }




    public static class AccountBuilder {

        public String username;
        public String email;
        public String password;

        public AccountBuilder(String email){
            this.email = email;
        }

        public AccountBuilder withPassword(String password){
            this.password = password;
            return this;
        }

        public AccountBuilder withName(String name){
            this.username = name;
            return this;
        }

        public AccountExtension build(){
            return new AccountExtension(this);
        }

    }



    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        printMessage("Setting up account: \n name: " + this.email + "\n email: " + this.email);
    }


    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        printMessage("Skipping account: \n name: " + this.email + "\n email: " + this.email);

    }


}




