package Week13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    public static String readContentFromFile(String path) {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = null;

        try {
            Path filePath = Paths.get(path);
            reader = new BufferedReader(new FileReader(filePath.toFile()));

            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return content.toString();
    }

    public static void writeContentToFile(String path, String content) {
        try {
            FileWriter writer = new FileWriter(path, false);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeContentToFile1(String path, String content) {
        try {
            FileWriter out = new FileWriter(path, true);
            out.write(content);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File findFileByName(String folderPath, String fileName) {
        File folder = new File(folderPath);

        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("The provided folder path is not a directory.");
        }

        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().equals(fileName)) {
                    return file;
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        // Test readContentFromFile method
        String filePath = "src\\Week13\\file.txt";
        String fileContent = Utils.readContentFromFile(filePath);
        System.out.println("File content:");
        System.out.println(fileContent);

        // Test writeContentToFile method
        String contentToWrite = "Hello, World!";
        String outputPath = "src\\Week13\\output.txt";
        Utils.writeContentToFile(outputPath, contentToWrite);
        System.out.println("Content written to file successfully.");

        // Test writeContentToFile1 method
        String additionalContent = "This is additional content.";
        Utils.writeContentToFile1(outputPath, additionalContent);
        System.out.println("Additional content written to file successfully.");

        // Test findFileByName method
        String folderPath = "src\\Week13";
        String fileName = "file.txt";
        File foundFile = Utils.findFileByName(folderPath, fileName);

        if (foundFile != null) {
            System.out.println("File found: " + foundFile.getAbsolutePath());
        } else {
            System.out.println("File not found.");
        }
    }
}