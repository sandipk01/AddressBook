package com.bridgelabz.addressbook.exception;

public class AddressBookException extends Exception{
    public enum TypeOfException{
        FILE_ALREADY_EXIST;
    }

    public TypeOfException type;

    public AddressBookException(String message, TypeOfException type) {
        super(message);
        this.type = type;
    }
}
