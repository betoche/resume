package org.als.processors.docs;

import lombok.Getter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DocxHandler {
    private final static String WORKSPACE_DIR = "/home/betoche_1/repositories/resume/workspace/";
    private String originalDocxFilePath = "";
    @Getter
    private File originalDocxFile = null;
    @Getter
    private File processedDocxFile = null;
    @Getter
    private File enDocxFile = null;
    @Getter
    private File esDocxFile = null;

    public DocxHandler(String originalDocxFilePath) throws IOException {
        this.originalDocxFilePath = originalDocxFilePath;

        generateWorkFiles();
    }

    private void generateWorkFiles() throws IOException {
        if(Files.exists(Paths.get(this.originalDocxFilePath))) {
            this.originalDocxFile = new File(this.originalDocxFilePath);
            String processedFileName = WORKSPACE_DIR + this.originalDocxFile.getName().replace(".docx", "-processed.docx");
            String enFileName = WORKSPACE_DIR + this.originalDocxFile.getName().replace(".docx", "-en.docx");
            String esFileName = WORKSPACE_DIR + this.originalDocxFile.getName().replace(".docx", "-es.docx");

            this.processedDocxFile = new File(processedFileName);
            this.enDocxFile = new File(enFileName);
            this.esDocxFile = new File(esFileName);

            FileUtils.copyFile(this.originalDocxFile, this.processedDocxFile);
            FileUtils.copyFile(this.originalDocxFile, this.enDocxFile);
            FileUtils.copyFile(this.originalDocxFile, this.esDocxFile);
        }
    }
}
