package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.model.Person;
import com.bridgelabz.addressbook.service.AddressBook;
import com.bridgelabz.addressbook.service.FileSystem;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static com.bridgelabz.addressbook.service.IFile.FILEPATH;

public class AddressBookController {
    private Map<Integer, File> addressBookMap;
    private AddressBook addressBook;
    private Scanner scanner;

    public AddressBookController() {
        this.addressBookMap = new LinkedHashMap<>();
        scanner = new Scanner(System.in);
    }

    public void dashBoard() throws IOException, AddressBookException {
        boolean exitSystem = false;
        while (!exitSystem) {
            System.out.println("1. for create new File");
            System.out.println("2. for open file");
            System.out.println("3. exit System");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("File name");
                    String fileName = scanner.next();
                    this.addressBook = new AddressBook(fileName);
                    addressBook.getFileSystem().createFile();
                    System.out.println("------------" + fileName + " file is created ---------------");
                    break;
                case 2:
                    openAddressBook();
                    break;
                case 3:
                    exitSystem = true;
                    break;
            }
        }
    }

    public void openAddressBook() throws IOException, AddressBookException {
        boolean exitAddressBook=false;
        while(!exitAddressBook) {
            System.out.println("Select File That you want to Open:");
            addressBookMap = getAddressBooks();
            for (Map.Entry<Integer, File> personEntry : addressBookMap.entrySet()) {
                System.out.println(personEntry.getKey() + "  " + personEntry.getValue().getName());
            }
            System.out.println("Enter index number: ");
            int index = scanner.nextInt();
            File file = addressBookMap.get(index);
            AddressBook addressBook = new AddressBook(file.getName().replace(".json", ""));
            System.out.println("---------------" + file.getName() + " is opened" + "----------------");
            System.out.println("1. Add new person");
            System.out.println("2. Edit person");
            System.out.println("3. Close address book");
            int choice=scanner.nextInt();
            switch (choice){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                   exitAddressBook=true;
            }
            dashBoard();
        }
    }


    public Map<Integer, File> getAddressBooks() {
        Map<Integer, File> fileMap = new LinkedHashMap<>();
        File folder = new File(FileSystem.FILEPATH);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                fileMap.put(i+1, listOfFiles[i]);
                //System.out.println(i + 1 + " : " + listOfFiles[i].getName());
            }
        }
        return fileMap;
    }
}
