package com.java.findpath;

import java.util.Arrays;
//역 13개
public class alt {
    private static final int INF = Integer.MAX_VALUE; // 무한대 값을 정의합니다. 두 정점이 직접 연결되어 있지 않을 경우 이 값을 사용합니다.
    private static final int VERTEX_COUNT = 6;  // 전체 정점(지하철 역)의 수를 나타냅니다.
 // 인접 행렬을 통해 각 정점(지하철 역)간의 거리를 나타냅니다.
 // 직접 연결되어 있지 않은 정점들 사이의 거리는 무한대(INF)로 설정합니다.
    private static int[][] adj = {
        {INF, 2, INF, 3, INF, INF},             
        {2, INF, 5, INF, 2, INF},          
        {INF, 5, INF, INF, INF, 3},          
        {3, INF, INF, INF, 2, INF},			
        {INF, 2, INF, 2, INF, 1},			 
        {INF, INF, 3, INF, 1 ,INF}			
        
    };
 // 각 정점의 이름(지하철 역 이름)을 저장하는 배열입니다.
    private static String[] stationNames = {
        "1", "2", "3", "4", "5", "6"
    };
 // 다익스트라 알고리즘을 구현한 함수입니다.
    private static void dijkstra(int startV, int endV) {
        boolean[] visited = new boolean[VERTEX_COUNT]; // 각 정점을 방문했는지 여부를 저장하는 배열입니다.
        int[] distance = new int[VERTEX_COUNT]; // 시작 정점으로부터 각 정점까지의 최단 거리를 저장하는 배열입니다.
        int[] routePairs = new int[VERTEX_COUNT]; // 각 정점의 이전 정점을 저장하는 배열입니다. 이를 통해 최단 경로를 추적합니다.
     // distance 배열을 무한대로 초기화합니다. 시작 정점의 거리는 0으로 설정합니다.
        Arrays.fill(distance, INF);
        distance[startV] = 0;
     // 모든 정점에 대해
        for (int i = 0; i < VERTEX_COUNT; i++) {
        	// 아직 방문하지 않은 정점 중 거리가 가장 짧은 정점을 찾습니다.
        	int min = INF;
            int minIdx = -1;
            
            for (int j = 0; j < VERTEX_COUNT; j++) {
                if (!visited[j] && distance[j] < min) {
                    min = distance[j];
                    minIdx = j;
                }
            }
         // 찾은 정점을 방문 처리합니다.
            visited[minIdx] = true;
         // 방문한 정점과 인접한 정점들의 거리를 갱신합니다.
            for (int j = 0; j < VERTEX_COUNT; j++) {
                if (!visited[j] && adj[minIdx][j] != INF && distance[minIdx] + adj[minIdx][j] < distance[j]) {
                    distance[j] = distance[minIdx] + adj[minIdx][j];
                    routePairs[j] = minIdx;
                }
            }
        }
     // 최단 경로를 추적하여 스택에 저장합니다.
        int[] routeStack = new int[VERTEX_COUNT];
        int routeCount = 0;
        for (int i = endV; i != startV; i = routePairs[i]) {
            routeStack[routeCount++] = i;
        }
        routeStack[routeCount++] = startV;
     // 저장된 최단 경로를 출력합니다.
        for (int i = routeCount - 1; i >= 0; i--) {
            String status = (i == routeCount - 1) ? "시작" : (i == 0) ? "도착" : "방문";
            System.out.printf("%s %s\n", stationNames[routeStack[i]], status);
        }
    }
    
    public static void main(String[] args) {
        dijkstra(0, 5);  
    }
}


