package com.path;


class Path {
    public static void main(String[] args) throws Exception {
        String pathFile = args[1];
        String cityFile = args[3];
        String source = args[args.length - 2];
        String destination = args[args.length - 1];
        ReadFile rdfile = new ReadFile();
        String pathContent = rdfile.readFile(pathFile);
        if (pathContent == null) {
            System.out.println("No database named " + pathFile + " found");
            return;
        }
        String cityContent = rdfile.readFile(cityFile);
        if (cityContent == null) {
            System.out.println("No database named " + cityFile + " found");
            return;
        }
        PathUtils paths = new PathUtils(pathContent, cityContent);
        if (!paths.isCityPresent(source)) {
            System.out.println("No city named " + source + " in database");
            return;
        }
        if (!paths.isCityPresent(destination)) {
            System.out.println("No city named " + destination + " in database");
            return;
        }
        if(args[4].equals("-a")) {
            for (String result : paths.getFullPath(source, destination))
                System.out.println(result);
            for (Integer cost : paths.TotalCost(source, destination)) {
                    System.out.println("TotalCost : " + cost);
                }

        }
        else {
            System.out.println(paths.getFullPath(source, destination).get(0));
            System.out.println("TotalCost : "+paths.TotalCost(source, destination).get(0));
        }

    }
}
