package com.bridgelabz.test;

import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.model.Person;
import com.bridgelabz.addressbook.service.AddressBook;
import com.bridgelabz.addressbook.service.FileSystem;
import com.bridgelabz.addressbook.service.IAddressBook;
import com.bridgelabz.addressbook.service.IFile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AddressBookTest {
    private IFile fileSystem;
    private IAddressBook addressBook;

    @Before
    public void setUp() {
        this.fileSystem = new FileSystem("file1");
        this.addressBook = new AddressBook("file1");
    }

    @Test
    public void givenJsonFile_WhenAddedInDirectory_ShouldReturnTrue() throws IOException, AddressBookException {
        Assert.assertEquals(true, fileSystem.createFile());
    }

    @Test
    public void givenJsonFileAddedInDirectory_WhenIfFileIsAlreadyExist_ShouldThrowFileAlreadyExistException() throws IOException {
        try {
            fileSystem.createFile();
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.TypeOfException.FILE_ALREADY_EXIST, e.type);
        }
    }

    @Test
    public void givenJsonFile_WhenAddedEntry_ShouldReturnCountOfEntry() throws IOException, AddressBookException {
        List<Person> personArrayList = new ArrayList<>();
        Person person = new Person("sandip", "kengar", "Mumbai", "Mumbai", "Maharashtra", "11457744", "7784858478");
        personArrayList.add(person);
        fileSystem.saveFile(personArrayList);
        Assert.assertEquals(1, fileSystem.readFile().size());
    }

    @Test
    public void givenJsonFile_WhenReadEntry_ShouldReturnCountOfEntry() throws IOException {
        Assert.assertEquals(1, fileSystem.readFile().size());
    }

    @Test
    public void givenAddressBook_WhenAddPerson_ShouldReturnCountOfEntry() throws IOException {
        Person person = new Person("Jhon", "Brike", "Mumbai", "Mumbai", "Maharashtra", "11457744", "9985624514");
        addressBook.addPerson(person);
        Assert.assertEquals(2, addressBook.getFileSystem().readFile().size());
    }

    @Test
    public void givenAddressBook_WhenEditPerson_ShouldReturnChangedObject() throws IOException, AddressBookException {
        Person person = new Person("Jhon", "Brike", "Mumbai", "Mumbai", "Maharashtra", "11457744", "8888888888");
        addressBook.editPerson(person, addressBook.getFileSystem().readFile().get(1).getPhoneNumber());
        List<Person> personList = addressBook.getFileSystem().readFile();
        Assert.assertEquals(true, addressBook.isPersonAdded(personList, person));
    }

    @Test
    public void givenAddressBook_WhenEditPersonGivenWrongInput_ShouldThrowInvalidNumberException() throws IOException {
        Person person = new Person("Jhon", "Brike", "Mumbai", "Mumbai", "Maharashtra", "11457744", "8888888888");
        try {
            addressBook.editPerson(person, "1245414784");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.TypeOfException.INVALID_NUMBER, e.type);
        }
    }
}
