package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.model.Person;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IFile {

    String FILEPATH = "src\\test\\resources\\";

    boolean createFile() throws IOException, AddressBookException;

    void saveFile(List<Person> personList) throws IOException;

    List<Person> readFile() throws IOException;

    File getFile();
}
