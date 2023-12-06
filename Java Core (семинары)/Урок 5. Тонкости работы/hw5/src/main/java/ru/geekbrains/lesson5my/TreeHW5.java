package ru.geekbrains.lesson5my;

import java.io.File;

public class TreeHW5 {
    public static void main(String[] args) {

        print(new File("./Java Core (семинары)"), "", true);
    }

    static void print(File file, String indent, boolean isLast){
        System.out.print(indent);
        if (isLast){
            System.out.print("└─");
            indent += "  ";
        }
        else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();
        if(files == null)
            return;
        int subDirTotal = 0;
        int subFileTotal = 0;
        for (int i = 0; i< files.length; i++) {
            if (files[i].isDirectory())
                subDirTotal++;
            if (files[i].isFile()) {
                subDirTotal++;
                subFileTotal++;
            }
        }

        int subDirCounter = 0;
        int subFileCounter = 0;
        for(int i = 0; i < files.length; i++){
            if (files[i].isDirectory()){
                print(files[i], indent, subDirTotal == ++subDirCounter);
            }
            if (files[i].isFile()){
                print(files[i], indent, subDirTotal == ++subFileCounter);
            }
        }
    }
}
