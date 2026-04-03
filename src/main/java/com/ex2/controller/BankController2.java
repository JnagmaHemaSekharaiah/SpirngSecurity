package com.ex2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController2
{
    @GetMapping("/balance")
    public String balanace()
    {
        return "Your  Balance :: 7884.00 INR";
    }

    @GetMapping("/transfer")
    public String amtTransfer()
    {
        return "Your Amount Transfered Successfullly" ;
    }

    @GetMapping("/login-check")
    public String login()
    {
        return "login sucessfully";
    }

    @GetMapping("/contact")
    public String getContactInfo()
    {
        return "Contact US :: abc@bank.com";
    }



}
