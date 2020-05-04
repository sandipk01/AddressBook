package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.model.Person;
import com.bridgelabz.addressbook.service.AddressBook;
import com.bridgelabz.addressbook.service.FileSystem;
import com.bridgelabz.addressbook.service.IAddressBook;
import com.bridgelabz.addressbook.service.IFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.bridgelabz.addressbook.service.IFile.FILEPATH;


public class AddressBookController {
    private Map<Integer, File> addressBookMap;
    private IAddressBook addressBook;
    private Scanner scanner;
    private IFile fileSystem;
    private Person person;
    private String firstName, lastName, address, city, state, zipCode, phoneNumber;

    public AddressBookController() {
        this.addressBookMap = new LinkedHashMap<>();
        scanner = new Scanner(System.in);
        fileSystem = new FileSystem();
    }

    public void applicationDashBoard() throws IOException, AddressBookException {
        boolean exitSystem = false;
        while (!exitSystem) {
            System.out.println("-----Welcome To Address Book Application----- \n1. Create new File." +
                    " \n2. Open existing file. \n3. Exit application.");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter file name");
                    String fileName = scanner.next();
                    this.addressBook = new AddressBook(fileName);
                    addressBook.getFileSystem().createFile();
                    System.out.println("------------" + fileName + " file is created ---------------");
                    break;
                case 2:
                    addressBook();
                    break;
                case 3:
                    exitSystem = true;
                    System.exit(0);
            }
        }
    }

    public void addressBook() throws IOException, AddressBookException {
        boolean exitAddressBook = false;
        while (!exitAddressBook) {
            System.out.println("Select File That you want to Open: \n0. Close");
            addressBookMap = fileSystem.getAddressBooks();
            for (Map.Entry<Integer, File> personEntry : addressBookMap.entrySet()) {
                System.out.println(personEntry.getKey() + "  " + personEntry.getValue().getName());
            }
            System.out.println("Enter index number: ");
            int index = scanner.nextInt();
            File file = addressBookMap.get(index);
            if (index == 0) {
                exitAddressBook = true;
            } else {
                IAddressBook addressBook = new AddressBook(file.getName().replace(".json", ""));
                System.out.println("---------------" + file.getName() + " is opened" + "----------------");
                System.out.println("1. Add new person \n2. Show persons \n3. Edit person" +
                        "\n4. Delete person \n5. Close address book");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Enter first name");
                        firstName = scanner.next();
                        System.out.println("Enter last name");
                        lastName = scanner.next();
                        System.out.println("Enter address");
                        address = scanner.next();
                        System.out.println("Enter city name");
                        city = scanner.next();
                        System.out.println("Enter state name");
                        state = scanner.next();
                        System.out.println("Enter zip code");
                        zipCode = scanner.next();
                        System.out.println("Enter phone number");
                        phoneNumber = scanner.next();
                        person = new Person(firstName, lastName, address, city, state, zipCode, phoneNumber);
                        addressBook.addPerson(person);
                        break;
                    case 2:
                        addressBook.showPerson();
                        break;
                    case 3:
                        addressBook.showPerson();
                        System.out.println("Enter the person index :");
                        index = scanner.nextInt();
                        List<Person> personList = addressBook.getFileSystem().readFile();
                        Person editPerson = personList.get(index-1);
                        System.out.println("1. Number \n2. address \n3. city" +
                                "\n4. zip code \n5. state");
                        choice = scanner.nextInt();
                        switch (choice) {
                            case 1:
                                System.out.println("Enter the new Number");
                                phoneNumber = scanner.next();
                                editPerson.setPhoneNumber(phoneNumber);
                                break;
                            case 2:
                                System.out.println("Enter the new Address");
                                address = scanner.next();
                                editPerson.setAddress(address);
                                break;
                            case 3:
                                System.out.println("enter the new city");
                                city = scanner.next();
                                editPerson.setCity(city);
                                break;
                            case 4:
                                System.out.println("Enter the new ZipCode");
                                zipCode = scanner.next();
                                editPerson.setZipCode(zipCode);
                                break;
                            case 5:
                                System.out.println("enter the new State");
                                state = scanner.next();
                                editPerson.setState(state);
                                break;
                        }
                        addressBook.editPerson(index-1 , editPerson);
                        addressBook.showPerson();
                        break;
                    case 4:
                        System.out.println("Enter The index :");
                        index=scanner.nextInt();
                        addressBook.deletePerson(index);
                        break;
                    case 5:
                        exitAddressBook = true;
                }
            }
        }
        applicationDashBoard();
    }
}
