package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddressBook implements IAddressBook{
    private FileSystem fileSystem;

    public AddressBook(String file) throws IOException {
        this.fileSystem = new FileSystem(file);
    }

    public FileSystem getFileSystem() {
        return fileSystem;
    }

    public List<Person> addPerson(Person person) throws IOException {
        List<Person> personList = null;
        if (fileSystem.getFile().length() == 0) {
            personList = new ArrayList<>();
            personList.add(person);
            fileSystem.saveFile(personList);
        } else {
            personList = fileSystem.readFile();
            personList.add(person);
            fileSystem.saveFile(personList);
        }
        return personList;
    }
}
