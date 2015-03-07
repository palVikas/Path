package com.path;

import java.util.*;

class Path {
    public static void main(String[] args) throws Exception{
        String file = args[0];
        String source = args[1];
        String destination = args[2];
        ReadFile rdfile = new ReadFile();
        String content = rdfile.readFile(file);
        PathUtils paths = new PathUtils(content);
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