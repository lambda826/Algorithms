package leetcode.graph.topological;

import java.util.*;

/*

You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients.
The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i].
A recipe can also be an ingredient for other recipes, i.e., ingredients[i] may contain a string that is in recipes.

You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.

Return a list of all the recipes that you can create. You may return the answer in any order.

Note that two recipes may contain each other in their ingredients.


Example 1:
    Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
    Output: ["bread"]
    Explanation:
    We can create "bread" since we have the ingredients "yeast" and "flour".

Example 2:
    Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
    Output: ["bread","sandwich"]
    Explanation:
    We can create "bread" since we have the ingredients "yeast" and "flour".
    We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".

Example 3:
    Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
    Output: ["bread","sandwich","burger"]
    Explanation:
    We can create "bread" since we have the ingredients "yeast" and "flour".
    We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
    We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".


Constraints:
    n == recipes.length == ingredients.length
    1 <= n <= 100
    1 <= ingredients[i].length, supplies.length <= 100
    1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
    recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
    All the values of recipes and supplies combined are unique.
    Each ingredients[i] does not contain any duplicate values.

*/
public class _2115_Find_All_Possible_Recipes_from_Given_Supplies {

    class Solution {
        public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
            Map<String, List<String>> out = new HashMap<>();
            Map<String, Set<String>> in = new HashMap<>();
            buildGraph(recipes, ingredients, out, in);
            List<String> res = new ArrayList<>();
            Queue<String> queue = new ArrayDeque<>();
            for (String supply : supplies) {
                if (in.containsKey(supply)) {
                    res.add(supply);
                }
                queue.offer(supply);
            }
            while (!queue.isEmpty()) {
                String supply = queue.poll();
                if (out.get(supply) != null) {
                    for (String next : out.get(supply)) {
                        in.get(next).remove(supply);
                        if (in.get(next).isEmpty()) {
                            queue.offer(next);
                            res.add(next);
                        }
                    }
                }
            }
            return res;
        }

        private void buildGraph(String[] recipes, List<List<String>> ingredients, Map<String, List<String>> out, Map<String, Set<String>> in) {
            for (int i = 0; i < recipes.length; ++i) {
                for (String ingredient : ingredients.get(i)) {
                    out.putIfAbsent(ingredient, new ArrayList<>());
                    out.get(ingredient).add(recipes[i]);
                }
                in.putIfAbsent(recipes[i], new HashSet<>(ingredients.get(i)));
            }
        }
    }
}
