package com.felipeflohr.autodrawer.logging;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.felipeflohr.autodrawer.logging.Logger.logger;

class CreateLogFile {

    private File file;

    CreateLogFile(String pathToFolder) {

        final String date = new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());
        String fileName = "log" + date + ".xml";

        final boolean windowsPath = pathToFolder.contains("\\");
        String path = windowsPath ? pathToFolder + "\\" + fileName : pathToFolder + "/" + fileName; // Windows paths are represented by '\' and UNIX '/'
        file = new File(path);

        try {
            if (!file.createNewFile()) {
                logger.log(LogLevel.FATAL, "Log file already exists.");
                throw new FileAlreadyExistsException("Log file already exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    File getFile() {
        return file;
    }
}
