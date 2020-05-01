package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.enums.Operation;
import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public class AddressBook implements IAddressBook {
    private IFile fileSystem;

    public AddressBook(String file) {
        this.fileSystem = new FileSystem(file);
    }

    public IFile getFileSystem() {
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

    public List<Person> editOrDeletePerson(Person person, String phoneNumber, Operation operation) throws IOException, AddressBookException {
        List<Person> personList = null;
        boolean flag = false;
        personList = fileSystem.readFile();
        ListIterator<Person> listItr = personList.listIterator();
        while (listItr.hasNext()) {
            if (listItr.next().getPhoneNumber().equals(phoneNumber)) {
                switch (operation) {
                    case EDIT:
                        listItr.set(person);
                        flag = true;
                        break;
                    case DELETE:
                        listItr.remove();
                        flag = true;
                        break;
                }
            }
        }
        if (flag == false)
            throw new AddressBookException("Given number is invalid", AddressBookException.TypeOfException.INVALID_NUMBER);
        fileSystem.saveFile(personList);
        return personList;
    }

    public boolean isPersonAdded(List<Person> personList, Person person) {
        return personList.stream().anyMatch(item -> item.equals(person));
    }
}
