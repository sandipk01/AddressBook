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


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.bridgelabz.addressbook.service.IFile.FILEPATH;

public class AddressBookTest {
    private FileSystem fileSystem;
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
        Person person = new Person("sandip", "kengar", "Mumbai", "Mumbai", "Maharashtra", "11457744", "9999999999");
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
        Person person3 = new Person("Vijay", "Crike", "Mumbai", "Mumbai", "Maharashtra", "11457744", "9985624516");
        Person person4 = new Person("Roni", "Arike", "Mumbai", "Mumbai", "Maharashtra", "11457744", "9985624518");
        addressBook.addPerson(person);
        addressBook.addPerson(person1);
        addressBook.addPerson(person2);
        addressBook.addPerson(person3);
        addressBook.addPerson(person4);
        Assert.assertEquals(fileSize + 5, addressBook.getFileSystem().readFile().size());
    }

    @Test
    public void givenAddressBook_WhenEditPerson_ShouldReturnChangedObject() throws IOException, AddressBookException {
        List<Person> personList = addressBook.getFileSystem().readFile();
        Person oldPerson = personList.get(0);
        Person person = new Person();
        person.setFirstName("Shon");
        person.setLastName("Hamilton");
        person.setPhoneNumber("9985624518");
        person.setCity("Pune");
        person.setAddress(oldPerson.getCity());
        person.setZipCode(oldPerson.getZipCode());
        person.setState(oldPerson.getState());
        person.setAddress(oldPerson.getAddress());
        addressBook.editPerson(0, person);
        List<Person> updatedList = addressBook.getFileSystem().readFile();
        Assert.assertEquals(true, addressBook.isPersonAdded(updatedList, person));
    }

    @Test
    public void givenAddressBook_WhenEditPersonGivenWrongIndex_ShouldThrowInvalidIndexException() throws IOException {
        Person person = new Person();
        person.setFirstName("Shon");
        person.setLastName("Hamilton");
        person.setPhoneNumber("9985624518");
        try {
            addressBook.editPerson(10, person);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.TypeOfException.INVALID_INDEX, e.type);
        }
    }

    @Test
    public void givenAddressBook_WhenDeletePerson_ShouldReturnTrue() throws IOException, AddressBookException {
        int fileSize = addressBook.getFileSystem().readFile().size();
        addressBook.deletePerson(5);
        Assert.assertEquals(fileSize - 1, addressBook.getFileSystem().readFile().size());
    }

    @Test
    public void givenAddressBook_WhenSortedByLastName_ShouldReturnSortedList() throws IOException {
        List<Person> originalList = addressBook.getFileSystem().readFile();
        List<Person> sortByLastName = addressBook.sortByLastName();
        Assert.assertEquals(true, originalList.containsAll(sortByLastName));
    }

    @Test
    public void givenAddressBook_WhenSortedByZipCode_ShouldReturnSortedList() throws IOException {
        List<Person> originalList = addressBook.getFileSystem().readFile();
        List<Person> sortByLastName = addressBook.sortByZipCode();
        Assert.assertEquals(true, originalList.containsAll(sortByLastName));
    }

    @Test
    public void givenAddressBook_WhenSaveAs_ShouldReturnNewFile() throws IOException {
        AddressBook addressBook=new AddressBook("file1");
        File oldFile = addressBook.getFileSystem().getFile();
        addressBook.getFileSystem().saveAs("file16");
        File newFile = addressBook.getFileSystem().getFile();
        Assert.assertEquals("file16.json", addressBook.getFileSystem().getFile().getName());
    }
}
