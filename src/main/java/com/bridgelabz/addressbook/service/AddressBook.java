package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;


public class AddressBook implements IAddressBook {
    private IFile fileSystem;

    public AddressBook(String file) {
        this.fileSystem = new FileSystem(file);
    }

    //Getting file system object
    public IFile getFileSystem() {
        return this.fileSystem;
    }

    //Adding new person in address book
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

    //Editing existing person from address book
    public List<Person> editPerson(int index, Person person) throws IOException, AddressBookException {
        List<Person> personList = fileSystem.readFile();
        if (personList.size() > index) {
            personList.set(index, person);
            fileSystem.saveFile(personList);
        } else {
            throw new AddressBookException("Invalid index", AddressBookException.TypeOfException.INVALID_INDEX);
        }
        return personList;
    }

    //Deleting person from address book
    public List<Person> deletePerson(int index) throws IOException, AddressBookException {
        List<Person> personList = fileSystem.readFile();
        personList.remove(index);
        fileSystem.saveFile(personList);
        return personList;
    }

    //Sorting person by last name
    public List<Person> sortByLastName() throws IOException {
        List<Person> personList = fileSystem.readFile();
        List<Person> sortedList = personList.stream()
                .sorted(Comparator.comparing(Person::getLastName))
                .collect(Collectors.toList());
        return sortedList;
    }

    //Sorting person by zip code
    public List<Person> sortByZipCode() throws IOException {
        List<Person> personList = fileSystem.readFile();
        List<Person> sortedList = personList.stream()
                .sorted(Comparator.comparing(Person::getZipCode))
                .collect(Collectors.toList());
        return sortedList;
    }

    //Checking if person is exist or not
    public boolean isPersonAdded(List<Person> personList, Person person) {
        return personList.stream().anyMatch(item -> item.equals(person));
    }
}
