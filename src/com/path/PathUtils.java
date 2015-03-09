package com.path;

import java.util.*;

public class PathUtils {

	public Map<String,List<String>> map = new HashMap<String,List<String>>();

    public Map<String,String> getCountryName = new HashMap<String,String>();

	public PathUtils(String pathContent,String cityContent){

        this.map = Database.pathStoreInDb(pathContent);
        this.getCountryName = Database.cityReader( cityContent);
	}

	Queue<String> path = new LinkedList<String>();

	public boolean isCityPresent(String city) {
        Set<String> keys = map.keySet();
		if(keys.contains(city))
			return true;
		else {
			for(String source : keys){
				List<String> destination = map.get(source);
				if(destination.contains(city))
					return true;
			}
		return false;
		}
	}

	public boolean isDirectPath(String from,String to) {
			path.add(from);
		return (isPath(from,to));
	}

	public boolean isPath(String source,String destination){
		Set<String> cities = map.keySet();
		if(isCityPresent(source) && isCityPresent(destination)){

			if(map.get(source).contains(destination)){
				path.add(destination);
				return true;
			}

			if(!map.get(source).contains(destination)){
				for(String city : cities){
					if(city.equals(source)) {
						List<String> lists = map.get(city);
						for(String list:lists){
							if(!path.contains(list)){
								path.add(list);
								return isPath(list,destination);
							}
						}
					}
				}
			}
		}
		return false;
	}

	public String getFullPath(String from,String to) {
		String fullpath = ""; 
		isDirectPath(from,to);
		int length = path.size();
		for(int i=0;i<length;i++){
            String cityName = path.poll().toString();
			if(i==0)
				fullpath +=""+cityName+"["+getCountryName.get(cityName)+"]";
			else
				fullpath +="-->"+cityName+"["+getCountryName.get(cityName)+"]";
		}
		return fullpath;
	}

}