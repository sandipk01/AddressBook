package com.bridgelabz.test;

import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.service.FileSystem;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class AddressBookTest {

    @Test
    public void givenJsonFile_WhenAddedInDirectory_ShouldReturnTrue() throws IOException, AddressBookException {
        FileSystem fileSystem = new FileSystem();
        Assert.assertEquals(true, fileSystem.createFile("file1"));
    }

    @Test
    public void givenJsonFileAddedInDirectory_WhenIfFileIsAlreadyExist_ShouldThrowFileAlreadyExistException() throws IOException {
        FileSystem fileSystem = new FileSystem();
        try {
            fileSystem.createFile("file1");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.TypeOfException.FILE_ALREADY_EXIST, e.type);
        }
    }
}
