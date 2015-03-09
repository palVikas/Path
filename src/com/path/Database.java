package com.path;

import java.util.*;

class Database{
	public static Map<String,List<String>> pathStoreInDb(String line){
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		String[] lines = line.split("\r\n");
		for(String singleLine:lines){
			String path[] = singleLine.split(",");
			List<String> destinations = map.get(path[0]);
			if(destinations == null){
				destinations = new ArrayList<String>();
				destinations.add(path[1]);				
				map.put(path[0],destinations);
			}
			else{
				destinations.add(path[1]);
			}
		}
		return map;
	}
    public static  Map<String,String> cityReader(String content){
        Map<String,String> readCountry = new HashMap<String,String>();
        String[] lines = content.split("\r\n");
        for(String line:lines){
            if(line!=null) {
                String path[] = line.split(",");
                readCountry.put(path[0], path[1]);
            }
        }
        return readCountry;
    }
}





