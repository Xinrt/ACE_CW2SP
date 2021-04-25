import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class CW2 {
    private static final int INF=1000000000;
    public static void main(String [] arg) {

//
        long startTime = System.currentTimeMillis();
        // read file
//        String filePath = readFile();
        String filePath = "src/main/resources/test_case2.txt";


        // store line information: vertexAndRelations.get(0)[0]: directed/undirected;
        // vertexAndRelations.get(0)[1]: start vertex
        // vertexAndRelations.get(0)[2]: end vertex
        // vertexAndRelations.get(0)[3]: weight
        // vertexAndRelations.size() = number of start/end vertex
        Map<Integer, String[]> vertexAndRelations;
        vertexAndRelations = dealFile(filePath);

        Map<String, Map<String, Integer>> simplifiedRelation;
        simplifiedRelation = simplifiedStartEndPair(vertexAndRelations);




        findShortestPath(simplifiedRelation);

        long endTime = System.currentTimeMillis();
        long time = endTime-startTime;
        System.out.println("time: " +time);
    }

    /**
     * initialize distance matrix and find the shortest path
     * @param simplifiedRelation a map stored simplified relations between vertices
     */
    private static void findShortestPath(Map<String, Map<String, Integer>> simplifiedRelation) {
        // vertices and corresponding subscripts
        // translate string vertex to a integer index: e.g. index 0 -> vertex "2"
        ArrayList<String> startVertex = new ArrayList<>();
        ArrayList<String> endVertex = new ArrayList<>();

        for (String key : simplifiedRelation.keySet()) {
            Map<String, Integer> end = simplifiedRelation.get(key);
            startVertex.add(key);
            endVertex.addAll(end.keySet());
        }

        //initialize two matrix
        //T: record transfer vertex   D: distance
        ArrayList<String> totalVertices = new ArrayList<>(startVertex);

        for (String vertex : endVertex) {
            if (!totalVertices.contains(vertex)) {
                totalVertices.add(vertex);
            }
        }

        // create two matrices to store transfer vertices and distance respectively
        int[][] T = new int[totalVertices.size()][totalVertices.size()];
        int[][] D = new int[totalVertices.size()][totalVertices.size()];

        // initialize whole distance matrix with infinite distances transfer matrix with index number
        for(int i=0;i<totalVertices.size();i++) {
            for(int j=0;j<totalVertices.size();j++) {
                D[i][j]= INF;
                T[i][j]=j;
            }
        }


        //s -> start; e -> end
        // add wights
        for (int s = 0; s < startVertex.size(); s++) {
            String start = startVertex.get(s); //start vertex
                for (int e = 0; e < totalVertices.size(); e++) {
                    String end = totalVertices.get(e); // end vertex
                    if(simplifiedRelation.get(start).containsKey(end)) {
                        D[s][e] = simplifiedRelation.get(start).get(end); //weight
                    }
                    T[s][e] = e;
                }
        }
        // update distance
        shortestPathByFloyd(totalVertices, T, D);
        // print out distance
        printDistance(totalVertices, T, D);
    }

    /**
     * find the shortest path between every two node by Floyd-Warshall algorithm
     * @param totalVertices an arraylist store all vertices
     * @param T transfer matrix
     * @param D distance matrix
     */
    private static void shortestPathByFloyd(ArrayList<String> totalVertices, int[][] T, int[][] D) {

        // t: transfer; s -> start; e -> end
        for(int t = 0; t < totalVertices.size(); t++){
            //v为起点
            for(int s = 0; s < totalVertices.size(); s++){
                for(int e = 0; e < totalVertices.size(); e++){
                    if(D[s][e] > (D[s][t] + D[t][e])) {
                        D[s][e] = D[s][t] + D[t][e];//update path
                        T[s][e] = T[s][t];//update vertices
                    }
                }
            }
        }

    }

    /**
     * print out total shortest distances
     * @param totalVertices an arraylist store all vertices
     * @param T transfer matrix
     * @param D distance matrix
     */
    private static void printDistance(ArrayList<String> totalVertices, int[][] T, int[][] D) {
        int totalDistance=0;
        for(int s = 0; s < totalVertices.size(); s++){
            for(int e = 0; e < totalVertices.size(); e++){
                if(D[s][e]== INF) {
//                    System.out.println("There is no path from " + startVertex.get(v) + " to " + endVertex.get(w));
                } else if(totalVertices.get(s).equals(totalVertices.get(e))) {
//                    System.out.println("The shortest path from " + totalVertices.get(s) + "->" + totalVertices.get(e) + " is 0");
                } else {
//                    System.out.println("The shortest path from " + totalVertices.get(s)+"->"+totalVertices.get(e)+" is "+D[s][e]);
                    totalDistance = totalDistance+ D[s][e];
                    int k = T[s][e];
//                    System.out.print("path: "+ totalVertices.get(s));
                    while(k != e){
//                        System.out.print("->"+totalVertices.get(k));//打印中间点
                        k = T[k][e];
                    }
//                    System.out.println("->"+totalVertices.get(e));
                }
            }
        }
//        for(int i=0; i<totalVertices.size(); i++) {
//            System.out.println(i+": "+totalVertices.get(i));
//        }
//        for(int i=0; i<totalVertices.size(); i++) {
//            System.out.println(Arrays.toString(D[i]));
//        }

        System.out.println(" Total shortest distance is " + totalDistance);
    }

    private static String readFile() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the file name：");
        String filePath = sc.nextLine();
        if(filePath==null) {
            System.out.println("The file name is invalid! May be you need to add .txt");
        } else {
            System.out.println("Your input file is: " + filePath);
        }
        return filePath;
    }

    /**
     * extract each start vertex with all its end vertices and related weight in hashmap
     * key (String): start vertex;
     * value(Map<String, Integer>): all end vertices and related weight
     * @param vertexAndRelations a hashmap stored all relations between start vertex and end vertex
     * @return a map stored simplified relations
     */
    private static Map<String, Map<String, Integer>> simplifiedStartEndPair(Map<Integer, String[]> vertexAndRelations) {
        // store exist vertex
        List<String> storedVertex = new ArrayList<>();
        Map<String, Map<String, Integer>> relation = new HashMap<>();
        for(int i = 0; i < vertexAndRelations.size(); i++) {
            // if a new vertex is not in the storage list
            if(!storedVertex.contains(vertexAndRelations.get(i)[1])) {
                Map<String, Integer> tempEndStore = new HashMap<>();
                for(int j = 0; j < vertexAndRelations.size(); j++) {
                    if(vertexAndRelations.get(j)[1].equals(vertexAndRelations.get(i)[1])) {
                        // only update when the new weight is smaller than the previous one
                        if(tempEndStore.containsKey(vertexAndRelations.get(j)[2]) && tempEndStore.get(vertexAndRelations.get(j)[2])<Integer.parseInt(vertexAndRelations.get(j)[3])) {
                            continue;
                        } else {
                            tempEndStore.put(vertexAndRelations.get(j)[2], Integer.valueOf(vertexAndRelations.get(j)[3]));
                        }
                    }
                }
                storedVertex.add(vertexAndRelations.get(i)[1]);
                relation.put(vertexAndRelations.get(i)[1], tempEndStore);
            }
        }

        return relation;
    }

    /**
     * separate information in a file
     * store each line in a hashmap, and store single character of a line in an string array
     * @param inputFilePath file path
     * @return the hashmap which store each line information
     */
    private static Map<Integer, String[]> dealFile(String inputFilePath) {
        File inputFile = new File(inputFilePath);
        //store each line in a hashmap, each item is an array store single information
        Map<Integer, String[]> result = new HashMap<>();

        // index of lines in file
        int numberOfVertex = 0;
        try {
            // read from file
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "GBK"));
            String oneLine;
            // read by line
            while((oneLine=bufferedReader.readLine())!=null) {
                // separate single information by space: "d","5","2","3"
                String[] oneRelation = oneLine.split("\\s+");

                // change undirected to directed, take them as 2 items in hashmap
                if(oneRelation[0].equals("u")) {
                    String[] temp1 = oneRelation.clone();
                    temp1[0] = "d";
                    result.put(numberOfVertex, temp1);
                    String[] temp2 = oneRelation.clone();
                    temp2[0] = "d";
                    temp2[1]=oneRelation[2];
                    temp2[2]=oneRelation[1];
                    result.put(numberOfVertex+1, temp2);
                    numberOfVertex=numberOfVertex+2;
                } else {
                    result.put(numberOfVertex, oneRelation);
                    numberOfVertex++;
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
