package com.path;


import java.util.*;

public class PathUtils {

	public Map<String,List<String>> map = new HashMap<String,List<String>>();

    public Map<String,String> getCountryName = new HashMap<String,String>();

	public PathUtils(String pathContent,String cityContent){

        this.map = Database.pathStoreInDb(pathContent);
        this.getCountryName = Database.cityReader( cityContent);
	}


	public boolean isCityPresent(String city) {
        Set<String> keys = map.keySet();
		if(keys.contains(city))
			return true;
		else {
			for(String source : keys){
				List<String> destination = map.get(source);
                if(destination.contains(city)) {
                    return true;
                }
			}
		return false;
		}
	}

	public List<Queue<String>> getDirectPath(String from,String to) {
        Queue<String> path = new LinkedList<String>();
        List<Queue<String>> AllRoots = new ArrayList<Queue<String>>();
		getPath(path, AllRoots, from, to);
        return AllRoots;
	}

	public void  getPath(Queue<String>path,List<Queue<String>> AllRoots,String source,String destination){
		path.add(source);
        Set<String> cities = map.keySet();
        if(isCityPresent(source) && isCityPresent(destination)){

            if(source.equals(destination)){
                AllRoots.add(new LinkedList<String>(path));
                path.remove(source);
                return;
            }
            for(String city : cities){
                if(city.equals(source)) {
                    List<String> lists = map.get(city);
                    for(String list:lists){
                        String[]list1 = list.split("==>");
                        if(!path.contains(list1[0])){
                            getPath(path, AllRoots, list1[0], destination);
                        }
                    }
                }

			}
		}
        path.remove(source);
	}

    public int giveCostOfTwoCities(String from,String to){
        String cost = "";
        List<String> destinations = map.get(from);
               for(String destination : destinations){
                      if(to.equals(destination.split("==>")[0]))
                               cost += destination.split("==>")[1];

               }
         return Integer.parseInt(cost);
    }


    public List<Integer> TotalCost(String from,String to){
        List<Queue<String>> AllRoots = getDirectPath(from,to);
        List<Integer> list = new ArrayList<Integer>();
        for(Queue<String> singleRoots:AllRoots) {
            int cost=0;
            String src = singleRoots.peek();
            int length = singleRoots.size();
            cost = getTotalCost(singleRoots, cost, src, length);
            list.add(cost);
        }
        return list;
    }

    private int getTotalCost(Queue<String> singleRoots, int cost, String src, int length) {
        for(int i=0;i<length;i++) {
            String des = singleRoots.poll();
            if(des!=null) {
                if (des == src)
                    des = singleRoots.poll();
                cost = cost + giveCostOfTwoCities(src, des);
                src = des;
            }
        }
        return cost;
    }

    public List<String> getFullPath(String from,String to) {
        List<Queue<String>> AllRoots = getDirectPath(from,to);
        List<String> list = new ArrayList<String>();
        for(Queue<String> singleRoots:AllRoots) {
            String result = "";
            int length = singleRoots.size();
            result += getFullPathInString(singleRoots, length);
            list.add(result);
        }
        return list;
    }

    private String getFullPathInString(Queue<String> singleRoots, int length) {
        String getfullpath = "";
        for (int i = 0; i < length; i++) {
            String cityName = singleRoots.poll();
            if (i == 0)
                getfullpath += "" + cityName + "[" + getCountryName.get(cityName) + "]";
            else
                getfullpath += "-->" + cityName + "[" + getCountryName.get(cityName) + "]";
        }
        return getfullpath;
    }
}

