package com.bridgelabz.addressbook.exception;

public class AddressBookException extends Exception{
    public enum TypeOfException{
        FILE_ALREADY_EXIST,INVALID_INDEX;
    }

    public TypeOfException type;

    public AddressBookException(String message, TypeOfException type) {
        super(message);
        this.type = type;
    }
}
