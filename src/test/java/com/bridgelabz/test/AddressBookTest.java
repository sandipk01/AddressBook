package com.bridgelabz.test;

import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.model.Person;
import com.bridgelabz.addressbook.service.AddressBook;
import com.bridgelabz.addressbook.service.FileSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AddressBookTest {

    @Test
    public void givenJsonFile_WhenAddedInDirectory_ShouldReturnTrue() throws IOException, AddressBookException {
        FileSystem fileSystem = new FileSystem("file");
        Assert.assertEquals(true, fileSystem.createFile());
    }

    @Test
    public void givenJsonFileAddedInDirectory_WhenIfFileIsAlreadyExist_ShouldThrowFileAlreadyExistException() throws IOException {
        FileSystem fileSystem = new FileSystem("file1");
        try {
            fileSystem.createFile();
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.TypeOfException.FILE_ALREADY_EXIST, e.type);
        }
    }

    @Test
    public void givenJsonFile_WhenAddedEntry_ShouldReturnCountOfEntry() throws IOException {
        FileSystem fileSystem = new FileSystem("file2");
        List<Person> personArrayList = new ArrayList<>();
        Person person = new Person("sandip", "kengar", "Mumbai", "Mumbai", "Maharashtra", "11457744", "7784858478");
        personArrayList.add(person);
        fileSystem.saveFile(personArrayList);
        Assert.assertEquals(1, fileSystem.readFile().size());
    }

    @Test
    public void givenJsonFile_WhenReadEntry_ShouldReturnCountOfEntry() throws IOException {
        FileSystem fileSystem = new FileSystem("file2");
        Assert.assertEquals(1, fileSystem.readFile().size());
    }

    @Test
    public void givenAddressBook_WhenAddPerson_ShouldReturnCountOfEntry() throws IOException {
        AddressBook addressBook=new AddressBook("file2");
        Person person = new Person("Jhon", "Brike", "Mumbai", "Mumbai", "Maharashtra", "11457744", "7784858478");
        addressBook.addPerson(person);
        Assert.assertEquals(2, addressBook.getFileSystem().readFile().size());
    }
}
