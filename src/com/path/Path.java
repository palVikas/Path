package com.path;

import java.util.*;

class Path {
    public static void main(String[] args) throws Exception{
        String pathFile = args[1];
        String cityFile = args[3];
        String source = args[4];
        String destination = args[5];
        ReadFile rdfile = new ReadFile();
        String pathContent = rdfile.readFile(pathFile);
        String cityContent = rdfile.readFile(cityFile);
        PathUtils paths = new PathUtils(pathContent,cityContent);
        if(!paths.isCityPresent(source)){
            System.out.println("No city named "+source+ " in database");
            return;
        }
        if(!paths.isCityPresent(destination)){
            System.out.println("No city named "+destination+ " in database");
            return;
        }
        else
            System.out.println(paths.getFullPath(source,destination));
    }
}