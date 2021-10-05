import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Stream;

public class CombineFiles {

    public static void singleFileCombine() throws IOException {
        String filename = "InkyDataset";
        String suffix = ".txt";
        String dir = "G:\\Downloads\\Inkbunny\\Converted";
        String resultName = "Dataset.txt";
        File my_dir = new File(dir);
        String[] file_names = my_dir.list();
        List<File> files = new ArrayList<>();
        for (String file_name: file_names){
            File my_file = new File(dir + "\\"+file_name);
            files.add(my_file);
        }
        combineFiles(filename + suffix, files);

    }
    public static void multiFileCombine() throws IOException {
        int filesInDataset = 729;
        int number = 1;
        String filename = "InkyDataset";
        String suffix = ".txt";
        String dir = "G:\\Downloads\\Inkbunny\\Converted";
        String resultName = "Dataset.txt";
        File my_dir = new File(dir);
        String[] file_names = my_dir.list();
        List<File> files = new ArrayList<>();
        for (String file_name: file_names){
            File my_file = new File(dir + "\\"+file_name);
            if(files.size()>=filesInDataset){
                combineFiles(filename + number + suffix, files);
                number++;
                files.clear();
            }
            files.add(my_file);
        }
        if(files.size()>0){
            combineFiles(filename + number + suffix, files);
        }
/*
        combineFiles(dir+"\\Part1\\Part1\\Part1","InkyDataset1.txt");
        combineFiles(dir+"\\Part1\\Part1\\Part2","InkyDataset2.txt");
        combineFiles(dir+"\\Part1\\Part2\\Part1","InkyDataset3.txt");
        combineFiles(dir+"\\Part1\\Part2\\Part2","InkyDataset4.txt");
        combineFiles(dir+"\\Part2\\Part1\\Part1","InkyDataset5.txt");
        combineFiles(dir+"\\Part2\\Part1\\Part2","InkyDataset6.txt");
        combineFiles(dir+"\\Part2\\Part2\\Part1","InkyDataset7.txt");
        combineFiles(dir+"\\Part2\\Part2\\Part2","InkyDataset8.txt");
        */
    }
    public static void main(String[] args) throws IOException {
       //multiFileCombine();
        singleFileCombine();
    }


    public static void combineFiles(String fileName, List<File> files) throws IOException {
        PrintWriter my_writer = new PrintWriter(fileName);
        for(File file:files){
            BufferedReader my_reader = new BufferedReader(new FileReader(file));
            String my_line = my_reader.readLine();
            my_writer.println("<|StartOfText|>");
            while (my_line != null){
                if(my_line.contains("<!DOCTYPE html>")){
                    break;
                }
                my_writer.println(my_line);
                my_line = my_reader.readLine();
            }
            my_writer.println("<|EndOfText|>");
            my_writer.flush();
        }
    }

   public static void combineFiles(String dir, String fileName) throws IOException {
       File my_dir = new File(dir);
       PrintWriter my_writer = new PrintWriter(fileName);
       String[] file_names = my_dir.list();
       for (String file_name: file_names){
           System.out.println("Content read from " + file_name);
           File my_file = new File(dir + "\\"+file_name);
           BufferedReader my_reader = new BufferedReader(new FileReader(my_file));
           //my_writer.println("The file contains " + file_name);
           String my_line = my_reader.readLine();
           while (my_line != null){
               my_writer.println(my_line);
               my_line = my_reader.readLine();
           }
           my_writer.println("<|endoftext|>");
           my_writer.flush();
       }
       System.out.println("All data from files have been read and " + my_dir.getName() + "merged");
   }
}
