package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.model.Person;

import java.io.IOException;
import java.util.List;

public interface IFile {

    String FILEPATH = "src\\test\\resources\\";

    boolean createFile(String file) throws IOException, AddressBookException;

    void saveFile(List<Person> personList) throws IOException;

    List<Person> readFile() throws IOException;
}
