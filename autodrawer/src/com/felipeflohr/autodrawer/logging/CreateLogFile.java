package com.felipeflohr.autodrawer.logging;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateLogFile {

    private File file;

    public CreateLogFile(String pathToFolder) {

        final String date = new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());
        String fileName = "log" + date + ".xml";

        final boolean windowsPath = pathToFolder.contains("\\");
        String path = windowsPath ? pathToFolder + "\\" + fileName : pathToFolder + "/" + fileName; // Windows paths are represented by '\' and UNIX '/'
        file = new File(path);

        try {
            if (!file.createNewFile()) {
                throw new FileAlreadyExistsException("Log file already exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }
}
