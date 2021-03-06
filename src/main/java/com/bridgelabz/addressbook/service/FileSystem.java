package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.model.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class FileSystem implements IFile {

    private ObjectMapper mapper;
    private File file;

    public FileSystem() { }

    public FileSystem(String file) {
        this.file = new File(FILEPATH + file + ".json");
        mapper = new ObjectMapper();
    }

    //Getting file path
    public File getFile() {
        return this.file;
    }

    //Creating new file
    public boolean createFile() throws IOException, AddressBookException {
        if (this.file.exists()) {
            throw new AddressBookException("File is Already Exist", AddressBookException.TypeOfException.FILE_ALREADY_EXIST);
        } else {
            this.file.createNewFile();
            return true;
        }
    }

    //Saving or writing file
    public void saveFile(List<Person> personList) throws IOException {
        mapper.writeValue(this.file, personList);
    }

    //Save as file
    public boolean saveAs(String newFileName) {
        File oldFile = this.file;
        File newFile = new File(FILEPATH + newFileName + ".json");
        if (oldFile.exists())
            return oldFile.renameTo(newFile);
        this.file = newFile;
        return false;
    }

    //Reading existing file
    public List<Person> readFile() throws IOException {
        return mapper.readValue(this.file, new TypeReference<ArrayList<Person>>() {
        });
    }

    //Getting list of files
    public Map<Integer, File> getAddressBooks() {
        AtomicInteger counter = new AtomicInteger(1);
        Map<Integer, File> files = new LinkedHashMap<>();
        File file = new File(FILEPATH);
        List<File> listOfFiles = Arrays.asList(file.listFiles());
        listOfFiles.stream().forEach((c) -> files.put(counter.getAndIncrement(),c));
        return files;
    }
}
