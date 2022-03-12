package org.example.fileSystem;

import org.example.Exceptions.FileOperationException;

import java.io.*;
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

    public static void saveSollution(String solPath, int sollutionLenght, String stepsToSolve) {
        try {
            File fout = new File(solPath);
            FileOutputStream fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            bw.write(String.valueOf(sollutionLenght));
            bw.newLine();
            bw.write(stepsToSolve);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveStats(String statPath, int sollutionLenght, int visitedStates, int processedStates, int maxDepth, double time) {
        try {
            File fout = new File(statPath);
            if(!fout.exists()) {
                System.out.println(fout.getAbsolutePath());
                System.out.println(fout.createNewFile());
            }
            FileOutputStream fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            bw.write(String.valueOf(sollutionLenght));
            bw.newLine();
            bw.write(String.valueOf(visitedStates));
            bw.newLine();
            bw.write(String.valueOf(processedStates));
            bw.newLine();
            bw.write(String.valueOf(maxDepth));
            bw.newLine();
            bw.write(String.valueOf(time));
            bw.newLine();

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void endWithError(String solPath, String statPath) {
        endSol(solPath);
        endStats(statPath);
    }

    private static void endStats(String stats) {
        try {
            File fout = new File(stats);
            FileOutputStream fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            bw.write("-1");
            bw.newLine();

            bw.close();
        } catch (IOException e) {
        }
    }

    private static void endSol(String sol) {
        try {
            File fout = new File(sol);
            FileOutputStream fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            bw.write("-1");
            bw.newLine();

            bw.close();
        } catch (IOException e) {
        }
    }


    @Override
    public void close() throws Exception {

    }
}
