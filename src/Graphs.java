import java.util.*;

public class Graphs {
    public static void main(String[] args) {

        List<List<Integer>> graph = new ArrayList<>();
        graph.add(new ArrayList<>(Arrays.asList(2, 3)));
        graph.add(new ArrayList<>(Arrays.asList(1, 3)));
        graph.add(new ArrayList<>(Arrays.asList(1, 2)));
        graph.add(new ArrayList<>(Arrays.asList(6,7)));
        graph.add(new ArrayList<>(Arrays.asList(6,7)));
        graph.add(new ArrayList<>(Arrays.asList(4,5,7)));
        graph.add(new ArrayList<>(Arrays.asList(4,5,6)));
        graph.add(new ArrayList<>(Arrays.asList(11)));
        graph.add(new ArrayList<>(Arrays.asList(10, 11)));
        graph.add(new ArrayList<>(Arrays.asList(9)));
        graph.add(new ArrayList<>(Arrays.asList(8,9)));


        System.out.println(find_connected_components(graph));
        // result is [[1, 2, 3], [4, 6, 5, 7], [8, 11, 9, 10]]
    }

    public static List<List<Integer>> find_connected_components(List<List<Integer>> graph) {
        Map<Integer, Boolean> visited = new HashMap<>();
        for (int i = 1; i <= graph.size(); i++) {
            visited.put(i, false);
        }
        List<List<Integer>> connected_components = new ArrayList<>();
        for (int i = 1; i <= graph.size(); i++) {
            Integer curKey = i;
            if (!visited.get(curKey)) {
                List<Integer> component = new ArrayList<>();
                dfs(graph, curKey, visited, component);
                connected_components.add(component);
            }
        }
        return connected_components;
    }
    public static void dfs(List<List<Integer>> graph, Integer cur,
                           Map<Integer, Boolean> visited, List<Integer> component) {
        visited.put(cur, true);
        component.add(cur);
        for (Integer i : graph.get(cur - 1)) {
            if (!visited.get(i)) {
                dfs(graph, i, visited, component);
            }
        }
    }

}