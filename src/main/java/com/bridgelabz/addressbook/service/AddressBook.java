package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.stream.Stream;

public class AddressBook implements IAddressBook {
    private FileSystem fileSystem;

    public AddressBook(String file) {
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

    public List<Person> editPerson(Person person, String phoneNumber) throws IOException {
        List<Person> personList = null;
        personList = fileSystem.readFile();
        ListIterator<Person> listItr = personList.listIterator();
        while (listItr.hasNext()) {
            if (listItr.next().getPhoneNumber().equals(phoneNumber)) {
                listItr.set(person);
            }
        }
        fileSystem.saveFile(personList);
        return personList;
    }

    public boolean isPersonAdded(List<Person> personList, Person person) {
        return personList.stream().anyMatch(item -> item.equals(person));
    }
}
