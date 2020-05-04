package com.bridgelabz.addressbook;

import com.bridgelabz.addressbook.controller.AddressBookController;
import com.bridgelabz.addressbook.exception.AddressBookException;

import java.io.IOException;

public class AddressBookApplication {
    public static void main(String[] args) throws IOException, AddressBookException {
        AddressBookController addressBookController=new AddressBookController();
        addressBookController.applicationDashBoard();
    }
}
