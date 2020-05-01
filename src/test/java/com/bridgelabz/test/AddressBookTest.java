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
}
