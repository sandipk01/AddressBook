package com.bridgelabz.addressbook.service;

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

    public FileSystem(String file) throws IOException {
        this.file = new File(FILEPATH + file + ".json");
        this.file.createNewFile();
        mapper = new ObjectMapper();
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
