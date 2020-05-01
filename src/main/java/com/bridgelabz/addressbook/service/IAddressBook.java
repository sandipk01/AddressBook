package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.model.Person;

import java.io.IOException;
import java.util.List;

public interface IAddressBook {
    List<Person> addPerson(Person person) throws IOException;
}
