//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.util.*;
//
////所有vertex距离其他所有vertex的最短距离
//// A -> B -> C : AB, AC, BA, BC, CA, CB
//public class CW2 {
//    public static void main(String [] arg) {
//        int INF = 1000000;
//
//        String filePath = "src/main/resources/test_case1.txt";
//
//        // store line information: vertexAndRelations.get(0)[0]: directed/undirected;
//        // vertexAndRelations.get(0)[1]: start vertex
//        // vertexAndRelations.get(0)[2]: end vertex
//        // vertexAndRelations.get(0)[3]: weight
//        // vertexAndRelations.size() = number of start/end vertex
//        Map<Integer, String[]> vertexAndRelations;
//        vertexAndRelations = dealFile(filePath);
//
//        Map<String, Map<String, Integer>> simplifiedRelation = new HashMap<>();
//        simplifiedRelation = simplifiedStartEndPair(vertexAndRelations);
//
//        //顶点和下标的对应
//        // translate string vertex to a integer index: e.g. index 0 -> vertex "2"
//        ArrayList<String> startVertex = new ArrayList<>();
//        ArrayList<String> endVertex = new ArrayList<>();
//        int startIndex = 0;
//        int endIndex = 0;
////        ArrayList<Integer> numberOfEndVertices = new ArrayList<>();
//
//        for (String key : simplifiedRelation.keySet()) {
//            Map<String, Integer> end = simplifiedRelation.get(key);
//            startVertex.add(key);
//            startIndex++;
////            numberOfEndVertices.add(end.size());
//            for (String key2 : end.keySet()) {
//                endVertex.add(key2);
//                endIndex++;
//            }
//        }
//
//        //initialize two matrix
//        //P: 记录中转点   D: 路径
////        int[][] P = new int[startVertex.size()][endVertex.size()];
////        int[][] D = new int[startVertex.size()][endVertex.size()];
//        ArrayList<String> totalVertices = new ArrayList<>();
//        totalVertices.addAll(startVertex);
////        totalVertices.addAll(endVertex);
//
//        for (String vertex : endVertex) {
//            if (!totalVertices.contains(vertex)) {
//                totalVertices.add(vertex);
//            }
//        }
//
//        for (String totalVertex : totalVertices) {
//            System.out.print(totalVertex + " ");
//        }
//        System.out.println();
//
//
////        int totalSize = totalVertices.size();
//        int[][] P = new int[totalVertices.size()][totalVertices.size()];
//        int[][] D = new int[totalVertices.size()][totalVertices.size()];
//
//
////        int count = 0;
////        for (int v = 0; v < startVertex.size(); v++) {
////            String start = startVertex.get(v); //start vertex
////            Map<String, Integer> tempEndList = new HashMap<>();
////            tempEndList = simplifiedRelation.get(start); // "2" 的那一行
////            for (int w = 0; w < endVertex.size(); w++) {
////                String end = endVertex.get(w); // end vertex
//////                System.out.println(simplifiedRelation.get(start).get(end));
////
//////                end==simplifiedRelation.get(start).
////                if (w < count) {
////                    D[v][w] = INF;
////                } else {
////                    if (simplifiedRelation.get(start).containsKey(end) && tempEndList != null) {
////                        D[v][w] = simplifiedRelation.get(start).get(end); //weight
////                        tempEndList.remove(endVertex.get(w));
////                        count++;
////                    } else {
////                        D[v][w] = INF;
////                    }
////                }
////
////                P[v][w] = w;
////            }
////        }
////        int count = 0;
////        for (int v = 0; v < startVertex.size(); v++) {
////            String start = startVertex.get(v); //start vertex
////            Map<String, Integer> tempEndList = new HashMap<>();
////            tempEndList = simplifiedRelation.get(start); // "2" 的那一行
////            for (int w = 0; w < endVertex.size(); w++) {
////                String end = endVertex.get(w); // end vertex
////                if (w < count) {
////                    D[v][w] = INF;
////                } else {
////                    if (simplifiedRelation.get(start).containsKey(end) && tempEndList != null) {
////                        D[v][w] = simplifiedRelation.get(start).get(end); //weight
////                        tempEndList.remove(endVertex.get(w));
////                        count++;
////                    } else {
////                        D[v][w] = INF;
////                    }
////                }
////
////                P[v][w] = w;
////            }
////        }
//
//        for(int i=0;i<totalVertices.size();i++) {
//            for(int j=0;j<totalVertices.size();j++) {
//                D[i][j]=INF;
//                P[i][j]=j;
//            }
//        }
//
//
//        int count = 0;
//        for (int v = 0; v < startVertex.size(); v++) {
//            String start = startVertex.get(v); //start vertex
////            Map<String, Integer> tempEndList = new HashMap<>();
////            if(simplifiedRelation.containsKey(start)) {
////                tempEndList = simplifiedRelation.get(start); // "2" 的那一行
////            for(String key: tempEndList.keySet()) {
////                System.out.print(key + " ");
////            }
////            System.out.println();
//            for (int w = 0; w < totalVertices.size(); w++) {
//                String end = totalVertices.get(w); // end vertex
//
//                if(simplifiedRelation.get(start).containsKey(end)) {
//                    D[v][w] = simplifiedRelation.get(start).get(end); //weight
//                }
//
//                P[v][w] = w;
//            }
////            }
//
//        }
//
//
//
//
//        System.out.println("D");
//        for (int v = 0; v < totalVertices.size(); v++) {
//            System.out.print(totalVertices.get(v) + "==>");
//            for (int w = 0; w < totalVertices.size(); w++) {
//                System.out.print(D[v][w]+ " ");
//            }
//            System.out.println();
//        }
//        System.out.println("P");
//        for (int v = 0; v < totalVertices.size(); v++) {
//            System.out.print(totalVertices.get(v) + "==>");
//            for (int w = 0; w < totalVertices.size(); w++) {
//                System.out.print(P[v][w]+ " ");
//            }
//            System.out.println();
//        }
//
//
//
////        // find the intermediate vertex
////        //k为中间点
////        for(int k = 0; k < startVertex.size(); k++){
////            //v为起点
////            for(int v = 0 ; v < startVertex.size(); v++){
////                //w为终点
////                for(int w =0; w < endVertex.size(); w++){
////
//////                    endVertex.get(w)  // end "0"
//////                    startVertex.get(v) // new start "3"
//////                    endVertex.get(k)  // end "0"
//////                    startVertex.get(k) // new start "3"
//////                    simplifiedRelation.get(startVertex.get(k))  // map of end
////                    boolean vk = simplifiedRelation.get(startVertex.get(v)).containsKey(endVertex.get(k)); // whether contain
////                    boolean kw = simplifiedRelation.get(startVertex.get(k)).containsKey(endVertex.get(w));
////
//////                    System.out.println(simplifiedRelation.get("2").containsKey("3"));
//////                    System.out.println(startVertex.get(0) + "->" +endVertex.get(5) + D[0][5]);
//////                    if(D[v][w] > (D[v][k] + D[k][w]) && vk && kw){
//////                        D[v][w] = D[v][k] + D[k][w];//更新最小路径
//////                        P[v][w] = P[v][k];//更新最小路径中间顶点
//////                    }
////                    if(D[v][w] > (D[v][k] + D[k][w])){
////                        System.out.println("初: " + startVertex.get(v) + endVertex.get(w) + " ==> " + D[v][w]);
////                        System.out.println("中1: " + startVertex.get(v) + endVertex.get(k) + " ==> " + D[v][k]);
////                        System.out.println("中2: " + startVertex.get(k) + endVertex.get(w) + " ==> " + D[k][w]);
////                        System.out.println("总: " + startVertex.get(k) + endVertex.get(w) + " ==> " + D[k][w]);
////
////
////
////                        D[v][w] = D[v][k] + D[k][w];//更新最小路径
////                        P[v][w] = P[v][k];//更新最小路径中间顶点
////                    }
////                }
////            }
////    }
//
//
//        // find interaction vertex
//        ArrayList<String> interactionVertices = new ArrayList<>();
//        for (String start : startVertex) {
//            for (String end : endVertex) {
//                if (start.equals(end) && !interactionVertices.contains(start)) {
//                    interactionVertices.add(start);
//                }
//            }
//        }
//
//        // print
//        System.out.println("interaction");
//        for (String interactionVertex : interactionVertices) {
//            System.out.print(interactionVertex + " ");
//        }
//        System.out.println();
//
//
////         find the intermediate vertex
//        //k为中间点
////        int newPath1=2*INF;
////        int newPath2=2*INF;
////        int newStart;
//        int newPath1=0;
//        int newPath2=0;
//        int newStart;
////        for(int k = 0; k < startVertex.size(); k++){
////            //v为起点
////            for(int v = 0 ; v < startVertex.size(); v++){
////                String currentStart = startVertex.get(v);
////                //w为终点
//////                for(int w =0; w < endVertex.size(); w++){
//////                    String inter = startVertex.get(k);
//////                    if(simplifiedRelation.get(currentStart).containsKey(inter)) {
//////                        newPath1 = D[v][k];
//////                    }
//////                    if(startVertex.contains(inter)) {
//////                        newStart = startVertex.indexOf(inter);
//////                        newPath2 = D[newStart][w];
//////                    }
//////                    if(D[v][w] > (newPath1 + newPath2)){
//////                        D[v][w] = newPath1 + newPath2;//更新最小路径
//////                        P[v][w] = P[v][k];//更新最小路径中间顶点
//////                    }
//////                }
////                for(int w =0; w < endVertex.size(); w++){
////                    String inter = startVertex.get(k);
////                    if(simplifiedRelation.get(currentStart).containsKey(inter)) {
////                        newStart = startVertex.indexOf(inter);
////
////                        if(D[v][w] > (D[v][k] + D[newStart][w])) {
////                            D[v][w] = D[v][k] + D[newStart][w];//更新最小路径
////                            P[v][w] = P[v][k];//更新最小路径中间顶点
////                        }
////                    }
////                }
////            }
////        for(int k = 0; k < startVertex.size(); k++){
////            //v为起点
////            for(int v = 0 ; v < startVertex.size(); v++){
////                for(int w =0; w < endVertex.size(); w++){
////                    String inter = startVertex.get(k);
////                    newStart = startVertex.indexOf(inter);
////                    if(D[v][w] > (D[v][k] + D[newStart][w])) {
////                        System.out.println("D[v][w]= "+startVertex.get(v)+endVertex.get(w)+"==>"+D[v][w]);
////                        System.out.println("D[v][k]= "+startVertex.get(v)+endVertex.get(k)+"==>"+D[v][k]);
////                        System.out.println("D[new][w]= "+startVertex.get(newStart)+endVertex.get(w)+"==>"+D[newStart][w]);
////                        D[v][w] = D[v][k] + D[newStart][w];//更新最小路径
////                        P[v][w] = P[v][k];//更新最小路径中间顶点
////                        System.out.println("New D[v][w]= "+startVertex.get(v)+endVertex.get(w)+"==>"+D[v][w]);
////                    }
////                }
////            }
////        }
//        for(int k = 0; k < totalVertices.size(); k++){
//            //v为起点
//            for(int v = 0 ; v < totalVertices.size(); v++){
//                for(int w =0; w < totalVertices.size(); w++){
//                    if(D[v][w] > (D[v][k] + D[k][w])) {
//                        D[v][w] = D[v][k] + D[k][w];//更新最小路径
//                        P[v][w] = P[v][k];//更新最小路径中间顶点
//                    }
//                }
//            }
//        }
//
//
//
////         find every shortest path and total shortest distance
////        int totalDistance=0;
////        for(int v=0; v < startVertex.size(); v++){
////            for(int w =0; w < endVertex.size(); w++){
////                if(D[v][w]==INF) {
//////                    System.out.println("There is no path from " + startVertex.get(v) + " to " + endVertex.get(w));
////                } else if(startVertex.get(v).equals(endVertex.get(w))) {
////                    System.out.println("The shortest path from " + startVertex.get(v) + "->" + endVertex.get(w) + " is 0");
////                } else {
////                    System.out.println("The shortest path from " + startVertex.get(v)+"->"+endVertex.get(w)+" is "+D[v][w]);
////                    totalDistance = totalDistance+D[v][w];
////                    int k = P[v][w];
////                    System.out.print("path: "+ startVertex.get(v));
////                    while(k != w){
////                        System.out.print("->"+startVertex.get(k));//打印中间点
////                        k = P[k][w];
////                    }
////                    System.out.println("->"+endVertex.get(w));
////                }
////            }
////        }
////        System.out.println("Total shortest distance is " + totalDistance);
//        int totalDistance=0;
//        for(int v=0; v < totalVertices.size(); v++){
//            for(int w =0; w < totalVertices.size(); w++){
//                if(D[v][w]==INF) {
////                    System.out.println("There is no path from " + startVertex.get(v) + " to " + endVertex.get(w));
//                } else if(totalVertices.get(v).equals(totalVertices.get(w))) {
//                    System.out.println("The shortest path from " + totalVertices.get(v) + "->" + totalVertices.get(w) + " is 0");
//                } else {
//                    System.out.println("The shortest path from " + totalVertices.get(v)+"->"+totalVertices.get(w)+" is "+D[v][w]);
//                    totalDistance = totalDistance+D[v][w];
//                    int k = P[v][w];
//                    System.out.print("path: "+ totalVertices.get(v));
//                    while(k != w){
//                        System.out.print("->"+totalVertices.get(k));//打印中间点
//                        k = P[k][w];
//                    }
//                    System.out.println("->"+totalVertices.get(w));
//                }
//            }
//        }
//        System.out.println("Total shortest distance is " + totalDistance);
//
//
//
////        int distance;
////        for(String key: simplifiedRelation.keySet()) {
////            Map<String, Integer>  end = simplifiedRelation.get(key);
////            for(String key2: end.keySet()) {
////                distance=end.get(key2);
////                System.out.println(distance);
////            }
////        }
//
//    }
//
//    /**
//     * extract each start vertex with all its end vertices and related weight in hashmap
//     * key (String): start vertex;
//     * value(Map<String, Integer>): all end vertices and related weight
//     * @param vertexAndRelations a hashmap stored all relations between start vertex and end vertex
//     * @return a map stored simplified relations
//     */
//    private static Map<String, Map<String, Integer>> simplifiedStartEndPair(Map<Integer, String[]> vertexAndRelations) {
//        // store exist vertex
//        List<String> storedVertex = new ArrayList<>();
//        Map<String, Map<String, Integer>> relation = new HashMap<>();
//        int index=0;
//        for(int i = 0; i < vertexAndRelations.size(); i++) {
//            // if a new vertex is not in the storage list
//            if(!storedVertex.contains(vertexAndRelations.get(i)[1])) {
//                Map<String, Integer> tempEndStore = new HashMap<>();
//                for(int j = 0; j < vertexAndRelations.size(); j++) {
//                    if(vertexAndRelations.get(j)[1].equals(vertexAndRelations.get(i)[1])) {
//                        tempEndStore.put(vertexAndRelations.get(j)[2], Integer.valueOf(vertexAndRelations.get(j)[3]));
//                    }
//                }
//                storedVertex.add(vertexAndRelations.get(i)[1]);
//                relation.put(vertexAndRelations.get(i)[1], tempEndStore);
//            }
//        }
//
//        // print relations
//        for(String key: relation.keySet()) {
//            Map<String, Integer>  end = relation.get(key);
//            System.out.println("start: " + key + "   end: " + end);
//        }
//        return relation;
//    }
//
//    /**
//     * separate information in a file
//     * store each line in a hashmap, and store single character of a line in an string array
//     * @param inputFilePath file path
//     * @return the hashmap which store each line information
//     */
//    private static Map<Integer, String[]> dealFile(String inputFilePath) {
//        File inputFile = new File(inputFilePath);
//        //store each line in a hashmap, each item is an array store single information
//        Map<Integer, String[]> result = new HashMap<>();
//
//        // index of lines in file
//        int numberOfVertex = 0;
//        try {
//            // read from file
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "GBK"));
//            String oneLine;
//            // read by line
//            while((oneLine=bufferedReader.readLine())!=null) {
//                // separate single information by space: "d","5","2","3"
//                String[] oneRelation = oneLine.split("\\s+");
//
//                // change undirected to directed, take them as 2 items in hashmap
//                if(oneRelation[0].equals("u")) {
//                    String[] temp1 = oneRelation.clone();
//                    temp1[0] = "d";
//                    result.put(numberOfVertex, temp1);
//                    String[] temp2 = oneRelation.clone();
//                    temp2[0] = "d";
//                    temp2[1]=oneRelation[2];
//                    temp2[2]=oneRelation[1];
//                    result.put(numberOfVertex+1, temp2);
//                    numberOfVertex=numberOfVertex+2;
//                } else {
//                    result.put(numberOfVertex, oneRelation);
//                    numberOfVertex++;
//                }
//            }
//            bufferedReader.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//}
