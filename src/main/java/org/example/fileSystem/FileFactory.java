package org.example.fileSystem;

import org.example.Exceptions.FileOperationException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileFactory implements AutoCloseable {

    private byte height = 0;
    private byte width = 0;
    private List<Byte> valueList = new ArrayList<>();

    public byte[][] getPuzzle(String path) throws IOException {
        File file = new File(path);
        if(!file.exists()) {
            throw new FileOperationException("There was a problem with reading the file ");
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            parseData(line);
        }
        byte[][] temp = new byte[height][width];
        int index = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                temp[i][j] = valueList.get(index);
                index ++;
            }
        }
        return temp;
    }

    private void parseData(String str) {
        Scanner lineScanner = new Scanner(str).useDelimiter(" ");
        if(height == 0 && width == 0) {
            this.height = Byte.valueOf(lineScanner.next());
            this.width = Byte.valueOf(lineScanner.next());
        }
        while (lineScanner.hasNext()) {
            valueList.add(Byte.valueOf(lineScanner.next()));
        }
    }

    public byte getHeight() {
        return height;
    }

    public byte getWidth() {
        return width;
    }



    @Override
    public void close() throws Exception {

    }
}
