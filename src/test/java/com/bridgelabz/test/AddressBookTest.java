package com.bridgelabz.test;

import com.bridgelabz.addressbook.service.FileSystem;
import com.bridgelabz.addressbook.service.IFile;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class AddressBookTest {

    @Test
    public void givenFile_WhenAddedInDirectory_ShouldReturnExactCount() throws IOException {
        long count;
        FileSystem fileSystem = new FileSystem("file1");
        FileSystem fileSysem = new FileSystem("file2");
        try (Stream<Path> files = Files.list(Paths.get(IFile.FILEPATH))) {
            count = files.count();
        }
        Assert.assertEquals(2, count);
    }
}
