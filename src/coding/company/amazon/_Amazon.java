///**
// *  @author Yunxiang He
// *  @date 11/01/2018
// */
//
//package coding.company.amazon;
//
//import algorithms.heap.GeneralHeap;
//import coding.company.amazon.questions.K_Substring_with_K_1_different_characters;
//import coding.lintcode.__0041_Maximum_Subarray;
//import coding.lintcode.__0077_Longest_Common_Subsequence;
//import coding.lintcode.__0079_Longest_Common_Substring;
//import coding.lintcode.__0533_Two_Sum_Closest_to_target;
//import coding.lintcode.__0597_Subtree_with_Maximum_Average;
//import coding.lintcode.__0609_Two_Sum_Less_than_or_equal_to_target;
//import coding.lintcode.__0612_K_Closest_Points;
//import coding.lintcode.__0613_High_Five;
//import coding.lintcode.__1369_Most_Common_Word;
//import coding.lintcode.__1377_Find_Substring;
//import coding.lintcode.__1478_Closest_Target_Value;
//import coding.lintcode.__1479_Can_Reach_The_Endpoint;
//import coding.lintcode.__1562_Number_of_restaurants;
//import coding.lintcode.__1563_Shortest_path_to_the_destination;
//import coding.lintcode.__1635_Max_Pair;
//import coding.lintcode.__1636_Aerial_Movie;
//import coding.lintcode.__1637_Tree_problem;
//import coding.lintcode.__1638_Least_Substring;
//import coding.lintcode.__1639_K_Substring_with_K_different_characters;
//import coding.problems.MST;
//import coding.problems.Maximum_Minimum_Path_in_Matrix;
//import coding.problems.Merge_Score;
//import coding.problems.Optimizing_Route;
//import coding.problems.Subtree_with_Maximum_Average;
//
//public interface _Amazon {
//
//    /**************** OA ****************/
//    __1639_K_Substring_with_K_different_characters __1639_K_Substring_with_K_different_characters = null; // k
//    K_Substring_with_K_1_different_characters K_Substring_with_K_1_different_characters = null; // k - 1
//    __1377_Find_Substring __1377_Find_Substring = null; // k
//    coding.leetcode.temp._0992_Subarrays_with_K_Different_Integers _0992_Subarrays_with_K_Different_Integers = null; ///////////////////////////////////
//    Subtree_with_Maximum_Average Subtree_with_Maximum_Average = null; // General, leaf not count
//    __0597_Subtree_with_Maximum_Average __0597_Subtree_with_Maximum_Average = null; // Binary Tree
//    __1637_Tree_problem _1637_Tree_problem = null; ///
//    __0613_High_Five __0613_High_Five = null;
//    __1562_Number_of_restaurants __1562_Number_of_restaurants = null;
//    __0612_K_Closest_Points __0612_K_Closest_Points = null;
//    coding.leetcode.temp._0973_K_Closest_Points_to_Origin _0973_K_Closest_Points_to_Origin = null;
//    Closest_Destinations ClosestDestinations = null;
//    Optimizing_Route Optimizing_Route = null;
//    __1636_Aerial_Movie __1636_Aerial_Movie = null;
//    __1638_Least_Substring __1638_Least_Substring = null;
//    coding.leetcode._02_string.palindrome._0005_Longest_Palindromic_Substring _0005_Longest_Palindromic_Substring = null;
//    coding.leetcode.temp._0819_Most_Common_Word _0819_Most_Common_Word = null;
//    __1369_Most_Common_Word __1369_Most_Common_Word = null;
//    coding.leetcode.temp._0937_Reorder_Log_Files _0937_Reorder_Log_Files = null;
//    MST MST = null;
//    Maximum_Minimum_Path_in_Matrix Maximum_Minimum_Path_in_Matrix = null;
//    __1563_Shortest_path_to_the_destination __1563_Shortest_path_to_the_destination = null;
//    __1479_Can_Reach_The_Endpoint __1479_Can_Reach_The_Endpoint = null;
//
//    /**************** VO ****************/
//    /**************** Onsite ************/
//    // String
//    coding.leetcode._02_string._0273_Integer_to_English_Words _0273_Integer_to_English_Words = null;
//    // Array
//    coding.leetcode.temp._0628_Maximum_Product_of_Three_Numbers _0628_Maximum_Product_of_Three_Numbers = null;
//    // Array | Sliding window
//    coding.leetcode.temp._0003_Longest_Substring_Without_Repeating_Characters _0003_Longest_Substring_Without_Repeating_Characters = null;
//    // Array | Intervals
//    coding.leetcode.temp._0252_Meeting_Rooms _0252_Meeting_Rooms = null;
//    coding.leetcode.temp._0253_Meeting_Rooms_II _0253_Meeting_Rooms_II = null;
//    // LinkedList
//    coding.leetcode._04_linkedList._0146_LRU_Cache _0146_LRU_Cache = null; // head, cache<key, doubleLinkedList>, +get/+put/-moveToHead/-removeTail
//    coding.leetcode.temp._0460_LFU_Cache _0460_LFU_Cache = null; //// head, cache<key, doubleLinkedList>, freq<frequency, doubleLinkedList>, +get/+put/-update/-insert/-removeTail
//    coding.leetcode._04_linkedList._0138_Copy_List_with_Random_Pointer _0138_Copy_List_with_Random_Pointer = null; // O(1) space is very great
//    coding.leetcode.temp._0160_Intersection_of_Two_Linked_Lists _0160_Intersection_of_Two_Linked_Lists = null;
//    // Tree
//    coding.leetcode.search.dfs._0543_Diameter_of_Binary_Tree _0543_Diameter_of_Binary_Tree = null;
//    coding.leetcode._10_tree.traversal.ordered._0124_Binary_Tree_Maximum_Path_Sum _0124_Binary_Tree_Maximum_Path_Sum = null;
//    coding.leetcode._10_tree.serialization._0297_Serialize_and_Deserialize_Binary_Tree _0297_Serialize_and_Deserialize_Binary_Tree = null;
//    coding.leetcode.tree.bst._0449_Serialize_and_Deserialize_BST _0449_Serialize_and_Deserialize_BST = null; // -----
//    coding.leetcode.temp._0652_Find_Duplicate_Subtrees _0652_Find_Duplicate_Subtrees = null;
//    // Tree | Trie
//    coding.leetcode.temp._0472_Concatenated_Words _0472_Concatenated_Words = null;
//    coding.leetcode.temp._0212_Word_Search_II _0212_Word_Search_II = null;
//    // Graph | Topological
//    coding.leetcode._11_graph.topologicalSort._0207_Course_Schedule _0207_Course_Schedule = null;
//    coding.leetcode._11_graph.topologicalSort._0210_Course_Schedule_II _0210_Course_Schedule_II = null;
//    // Graph | Shortest path
//    coding.leetcode.temp._0505_The_Maze_II _0505_The_Maze_II = null;
//    // DFS, BFS
//    coding.leetcode.temp._0490_The_Maze _0490_The_Maze = null;
//    coding.leetcode.temp._0079_Word_Search _0079_Word_Search = null;
//    coding.leetcode.temp._0130_Surrounded_Regions _0130_Surrounded_Regions = null;
//    coding.leetcode.graph.search.matrix._0200_Number_of_Islands _0200_Number_of_Islands = null;
//    coding.leetcode._0127_Word_Ladder _0127_Word_Ladder = null;
//    // DP
//    coding.leetcode.temp._0139_Word_Break _0139_Word_Break = null;
//    coding.leetcode.temp._0983_Minimum_Cost_For_Tickets _0983_Minimum_Cost_For_Tickets = null;
//    __0079_Longest_Common_Substring __0079_Longest_Common_Substring = null;
//    __0077_Longest_Common_Subsequence __0077_Longest_Common_Subsequence = null;
//    coding.leetcode._04_dynamicProgramming.oneDimension.optimized._0053_Maximum_Subarray _0053_Maximum_Subarray = null;
//    coding.leetcode.temp._0152_Maximum_Product_Subarray _0152_Maximum_Product_Subarray = null;
//    __0041_Maximum_Subarray __0041_Maximum_Subarray = null;
//    // Stack
//    coding.leetcode._03_twoPointer._0042_Trapping_Rain_Water _0042_Trapping_Rain_Water = null;
//    // Monotous Stack
//    coding.leetcode.temp._0239_Sliding_Window_Maximum _0239_Sliding_Window_Maximum = null;
//    coding.leetcode.temp._0496_Next_Greater_Element_I _0496_Next_Greater_Element_I = null;
//    coding.leetcode.temp._0503_Next_Greater_Element_II _0503_Next_Greater_Element_II = null;
//    // Heap
//    coding.leetcode.temp._0703_Kth_Largest_Element_in_a_Stream _0703_Kth_Largest_Element_in_a_Stream = null;
//    coding.leetcode.temp._0215_Kth_Largest_Element_in_an_Array _0215_Kth_Largest_Element_in_an_Array = null;
//    coding.leetcode.temp._0295_Find_Median_from_Data_Stream _0295_Find_Median_from_Data_Stream = null; // coding: design a class which when having a stream of data coming in, you could get min, max, avg, median
//    // Hashing
//    coding.leetcode.temp._0387_First_Unique_Character_in_a_String _0387_First_Unique_Character_in_a_String = null;
//    // Design
//    coding.leetcode.temp._0380_Insert_Delete_GetRandom_O1 _0380_Insert_Delete_GetRandom_O1 = null;
//    coding.leetcode.temp._0706_Design_HashMap _0706_Design_HashMap = null;
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//    coding.leetcode._03_hash._0001_Two_Sum _0001_Two_Sum = null; // OA
//    coding.leetcode.temp._0015_3Sum _0015_3Sum = null;
//    coding.leetcode.binarySearch._0167_Two_Sum_II_Input_array_is_sorted _0167_Two_Sum_II_Input_array_is_sorted = null;
//    coding.leetcode.temp._0170_Two_Sum_III_Data_structure_design _0170_Two_Sum_III_Data_structure_design = null;
//    __0609_Two_Sum_Less_than_or_equal_to_target __0609_Two_Sum_Less_than_or_equal_to_target = null; //// 0A
//    __1478_Closest_Target_Value _1478_Closest_Target_Value = null; // OA
//    __0533_Two_Sum_Closest_to_target __0533_Two_Sum_Closest_to_target = null; // OA
//    __1635_Max_Pair __1635_Max_Pair = null; /// OA
//    coding.leetcode.temp._0056_Merge_Intervals _0056_Merge_Intervals = null;
//
//    coding.leetcode.tree.bst._0272_Closest_Binary_Search_Tree_Value_II _0272_Closest_Binary_Search_Tree_Value_II = null;
//    coding.leetcode.tree.lowest_common_ancesstor._0236_Lowest_Common_Ancestor_of_a_Binary_Tree _0236_Lowest_Common_Ancestor_of_a_Binary_Tree = null;
//    coding.leetcode.temp._0101_Symmetric_Tree _0101_Symmetric_Tree = null;
//    coding.leetcode.temp._0257_Binary_Tree_Paths _0257_Binary_Tree_Paths = null;
//    coding.leetcode.temp._0238_Product_of_Array_Except_Self _0238_Product_of_Array_Except_Self = null;
//
//    coding.leetcode.temp._0348_Design_Tic_Tac_Toe _0348_Design_Tic_Tac_Toe = null;
//    coding.leetcode.temp._0078_Subsets _0078_Subsets = null;
//    coding.leetcode.temp._0794_Valid_Tic_Tac_Toe_State _0794_Valid_Tic_Tac_Toe_State = null;
//    coding.leetcode.temp._0155_Min_Stack _0155_Min_Stack = null;
//    coding.leetcode._01_array._0560_Subarray_Sum_Equals_K _0560_Subarray_Sum_Equals_K = null;
//    coding.leetcode.temp._0695_Max_Area_of_Island _0695_Max_Area_of_Island = null;
//    coding.leetcode.temp._0071_Simplify_Path _0071_Simplify_Path = null;
//    coding.leetcode.temp._0104_Maximum_Depth_of_Binary_Tree _0104_Maximum_Depth_of_Binary_Tree = null; // height
//    coding.leetcode.temp._0206_Reverse_Linked_List _0206_Reverse_Linked_List = null;
//    coding.leetcode.temp._0131_Palindrome_Partitioning _0131_Palindrome_Partitioning = null;
//    coding.leetcode.temp._0019_Remove_Nth_Node_From_End_of_List _0019_Remove_Nth_Node_From_End_of_List = null;
//    coding.leetcode.temp._0545_Boundary_of_Binary_Tree _0545_Boundary_of_Binary_Tree = null;
//    coding.leetcode.temp._0951_Flip_Equivalent_Binary_Trees _0951_Flip_Equivalent_Binary_Trees = null;
//    coding.leetcode.temp._0763_Partition_Labels _0763_Partition_Labels = null;
//    coding.leetcode.temp._0909_Snakes_and_Ladders _0909_Snakes_and_Ladders = null;
//    coding.leetcode.temp._0145_Binary_Tree_Postorder_Traversal _0145_Binary_Tree_Postorder_Traversal = null;
//    coding.leetcode.tree.bst._0098_Validate_Binary_Search_Tree _0098_Validate_Binary_Search_Tree = null;
//    coding.leetcode.temp._0151_Reverse_Words_in_a_String _0151_Reverse_Words_in_a_String = null;
//    coding.leetcode._0314_Binary_Tree_Vertical_Order_Traversal _0314_Binary_Tree_Vertical_Order_Traversal = null;
//    coding.leetcode.temp._0049_Group_Anagrams _0049_Group_Anagrams = null;
//    coding.leetcode.temp._0398_Random_Pick_Index _0398_Random_Pick_Index = null;
//    coding.leetcode.temp._0126_Word_Ladder_II _0126_Word_Ladder_II = null;
//    coding.leetcode._04_sorting.mergeSort._0021_Merge_Two_Sorted_Lists _0021_Merge_Two_Sorted_Lists = null;
//    coding.leetcode.temp._0110_Balanced_Binary_Tree _0110_Balanced_Binary_Tree = null;
//    coding.leetcode.temp._0277_Find_the_Celebrity _0277_Find_the_Celebrity = null;
//    coding.leetcode.temp._0002_Add_Two_Numbers _0002_Add_Two_Numbers = null;
//    coding.leetcode.tree.lowest_common_ancesstor._0235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree _0235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree = null;
//    coding.leetcode._02_string.parentheses._0032_Longest_Valid_Parentheses _0032_Longest_Valid_Parentheses = null;
//    coding.leetcode.temp._0026_Remove_Duplicates_from_Sorted_Array _0026_Remove_Duplicates_from_Sorted_Array = null;
//    coding.leetcode.temp._0103_Binary_Tree_Zigzag_Level_Order_Traversal _0103_Binary_Tree_Zigzag_Level_Order_Traversal = null;
//    coding.leetcode.temp._0165_Compare_Version_Numbers _0165_Compare_Version_Numbers = null;
//    coding.leetcode.temp._0412_Fizz_Buzz _0412_Fizz_Buzz = null;
//    coding.leetcode._06_bfs_dfs._0199_Binary_Tree_Right_Side_View _0199_Binary_Tree_Right_Side_View = null;
//    coding.leetcode.temp._0284_Peeking_Iterator _0284_Peeking_Iterator = null;
//    coding.leetcode.temp._0100_Same_Tree _0100_Same_Tree = null;
//    coding.leetcode.temp._0179_Largest_Number _0179_Largest_Number = null;
//    coding.leetcode.temp._0033_Search_in_Rotated_Sorted_Array _0033_Search_in_Rotated_Sorted_Array = null;
//    coding.leetcode._04_sorting.mergeSort._0023_Merge_k_Sorted_Lists _0023_Merge_k_Sorted_Lists = null;
//    coding.leetcode.temp._0107_Binary_Tree_Level_Order_Traversal_II _0107_Binary_Tree_Level_Order_Traversal_II = null;
//    coding.leetcode.temp._0611_Valid_Triangle_Number _0611_Valid_Triangle_Number = null;
//    coding.leetcode.temp._0485_Max_Consecutive_Ones _0485_Max_Consecutive_Ones = null;
//    coding.leetcode.temp._0487_Max_Consecutive_Ones_II _0487_Max_Consecutive_Ones_II = null;
//    coding.leetcode.temp._1004_Max_Consecutive_Ones_III _1004_Max_Consecutive_Ones_III = null;
//    coding.leetcode.temp._0062_Unique_Paths _0062_Unique_Paths = null;
//    coding.leetcode.temp._0048_Rotate_Image _0048_Rotate_Image = null;
//    coding.leetcode.temp._0075_Sort_Colors _0075_Sort_Colors = null;
//    coding.leetcode.tree.traversal.level_order._0102_Binary_Tree_Level_Order_Traversal _0102_Binary_Tree_Level_Order_Traversal = null;
//    coding.leetcode.temp._0429_N_ary_Tree_Level_Order_Traversal _0429_N_ary_Tree_Level_Order_Traversal = null;
//    coding.leetcode.temp._0162_Find_Peak_Element _0162_Find_Peak_Element = null;
//    coding.leetcode.temp._0205_Isomorphic_Strings _0205_Isomorphic_Strings = null;
//    coding.leetcode.temp._0249_Group_Shifted_Strings _0249_Group_Shifted_Strings = null;
//    coding.leetcode.temp._0445_Add_Two_Numbers_II _0445_Add_Two_Numbers_II = null;
//    coding.leetcode.temp._0226_Invert_Binary_Tree _0226_Invert_Binary_Tree = null;
//    coding.leetcode.temp._0336_Palindrome_Pairs _0336_Palindrome_Pairs = null;
//    coding.leetcode.temp._0149_Max_Points_on_a_Line _0149_Max_Points_on_a_Line = null;
//    Merge_Score MergeScore = null;
//    GeneralHeap<Integer> MaxHeap = null;
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//}
