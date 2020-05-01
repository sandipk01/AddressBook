package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.model.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileSystem implements IFile {

    private ObjectMapper mapper;
    private File file;

    public FileSystem() throws IOException {
        mapper = new ObjectMapper();
    }

    public boolean createFile(String file) throws IOException, AddressBookException {
        this.file = new File(FILEPATH + file + ".json");
        if (this.file.exists()) {
            throw new AddressBookException("File is Already Exist", AddressBookException.TypeOfException.FILE_ALREADY_EXIST);
        } else {
            this.file.createNewFile();
            return true;
        }
    }

    public File getFile() {
        return file;
    }

    public void saveFile(List<Person> personList) throws IOException {
        mapper.writeValue(file, personList);
    }

    public List<Person> readFile() throws IOException {
        return mapper.readValue(file, new TypeReference<ArrayList<Person>>() {
        });
    }
}
