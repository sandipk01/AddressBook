package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.model.Person;

import java.io.IOException;
import java.util.List;

public interface IAddressBook {

    List<Person> addPerson(Person person) throws IOException;

    List<Person> editPerson(Person person, String phoneNumber) throws IOException, AddressBookException;

    List<Person> deletePerson(String phoneNumber) throws IOException, AddressBookException;

    IFile getFileSystem();

    boolean isPersonAdded(List<Person> personList, Person person);

    List<Person> sortByLastName() throws IOException;
}
