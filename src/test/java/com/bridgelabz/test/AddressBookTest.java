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
        FileSystem fileSystem1 = new FileSystem("file2");
        Assert.assertEquals(true, fileSystem1.createFile());
        fileSystem1.getFile().delete();
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
    public void givenJsonFile_WhenAddedEntry_ShouldReturnCountOfEntry() throws IOException {
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
        int fileSize = addressBook.getFileSystem().readFile().size();
        Person person = new Person("Jhon", "Rrike", "Mumbai", "Mumbai", "Maharashtra", "11457744", "9985624514");
        Person person1 = new Person("Jhon", "zrike", "Mumbai", "Mumbai", "Maharashtra", "11457744", "9985624514");
        Person person2 = new Person("Jhon", "Brike", "Mumbai", "Mumbai", "Maharashtra", "11457744", "9985624515");
        Person person3 = new Person("Jhon", "Crike", "Mumbai", "Mumbai", "Maharashtra", "11457744", "9985624516");
        Person person4 = new Person("Jhon", "Arike", "Mumbai", "Mumbai", "Maharashtra", "11457744", "9985624518");
        addressBook.addPerson(person);
        addressBook.addPerson(person1);
        addressBook.addPerson(person2);
        addressBook.addPerson(person3);
        addressBook.addPerson(person4);
        Assert.assertEquals(fileSize + 5, addressBook.getFileSystem().readFile().size());
    }

    @Test
    public void givenAddressBook_WhenEditPerson_ShouldReturnChangedObject() throws IOException, AddressBookException {
        Person person = new Person("Jhon", "Brike", "Mumbai", "Mumbai", "Maharashtra", "11457744", "5656565656");
        addressBook.editPerson(person, addressBook.getFileSystem().readFile().get(0).getPhoneNumber());
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

    @Test
    public void givenAddressBook_WhenDeletePerson_ShouldReturnTrue() throws IOException, AddressBookException {
        int fileSize = addressBook.getFileSystem().readFile().size();
        addressBook.deletePerson(addressBook.getFileSystem().readFile().get(0).getPhoneNumber());
        Assert.assertEquals(fileSize - 1, addressBook.getFileSystem().readFile().size());
    }

    @Test
    public void givenAddressBook_WhenSortedByLastName_ShouldReturnSortedList() throws IOException {
        List<Person> originalList = addressBook.getFileSystem().readFile();
        List<Person> sortByLastName = addressBook.sortByLastName();
        Assert.assertEquals(true, originalList.containsAll(sortByLastName));
    }
}
