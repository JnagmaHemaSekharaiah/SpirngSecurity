package com.dbauthentication.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;



public class UserNotFoundException  extends RuntimeException{

   UserNotFoundException(String mesg)
   {
       super(mesg);
   }




}
