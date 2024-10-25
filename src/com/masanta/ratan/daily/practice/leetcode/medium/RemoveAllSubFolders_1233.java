package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveAllSubFolders_1233 {

    /**
     * Problem statement:
     * 1233. Remove Sub-Folders from the Filesystem
     * Given a list of folders folder, return the folders after removing all sub-folders in those folders. You may return the answer in any order.
     *
     * If a folder[i] is located within another folder[j], it is called a sub-folder of it. A sub-folder of folder[j] must start with folder[j], followed by a "/". For example, "/a/b" is a sub-folder of "/a", but "/b" is not a sub-folder of "/a/b/c".
     *
     * The format of a path is one or more concatenated strings of the form: '/' followed by one or more lowercase English letters.
     *
     * For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.
     *
     *
     * Example 1:
     *
     * Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
     * Output: ["/a","/c/d","/c/f"]
     * Explanation: Folders "/a/b" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
     * Example 2:
     *
     * Input: folder = ["/a","/a/b/c","/a/b/d"]
     * Output: ["/a"]
     * Explanation: Folders "/a/b/c" and "/a/b/d" will be removed because they are subfolders of "/a".
     * Example 3:
     *
     * Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
     * Output: ["/a/b/c","/a/b/ca","/a/b/d"]
     *
     *
     * Constraints:
     *
     * 1 <= folder.length <= 4 * 104
     * 2 <= folder[i].length <= 100
     * folder[i] contains only lowercase letters and '/'.
     * folder[i] always starts with the character '/'.
     * Each folder name is unique.
     */

    public static void main(String[] args) {
        String[] folder1 = new String[] {"/a","/a/b","/c/d","/c/d/e","/c/f"};
        String[] folder2 = new String[] {"/a","/a/b/c","/a/b/d"};

        System.out.println(STR."For second method, folder structure \{Arrays.toString(folder1)} , the final array after removal is : \{RemoveAllSubFolders_1233.removeSubfolders1(folder1)}");
        System.out.println(STR."For second method, folder structure \{Arrays.toString(folder2)} , the final array after removal is : \{RemoveAllSubFolders_1233.removeSubfolders1(folder2)}");

    }

    /**
     *
     * Takes a list of folder paths as input
     * Returns a list of folder paths with all subfolders removed
     * Example input: ["/a","/a/b","/c/d","/c/d/e","/c"]
     * Sorting Step:
     * folder.sort()
     * Sorts folders lexicographically
     * This ensures parent folders come before their subfolders
     * Example after sort: ["/a", "/a/b", "/c", "/c/d", "/c/d/e"]
     * Initialize Result:
     * ans = [folder[0]]
     * Starts the result list with the first folder
     * First folder can't be a subfolder of anything since list is sorted
     * Iteration and Checking:
     * for i in range(1, len(folder)):
     *     last_folder = ans[-1] + '/'
     *     if not folder[i].startswith(last_folder):
     *         ans.append(folder[i])
     * Iterates through remaining folders
     * For each folder:
     * Gets the last added folder and adds '/' to it
     * Checks if current folder starts with last_folder path
     * If it doesn't start with last_folder, it's not a subfolder and gets added
     * How the Check Works:
     * Example with "/a" as last_folder:
     * "/a/" + "b" would be a subfolder (starts with "/a/")
     * "/c" would not be a subfolder (doesn't start with "/a/")
     * Example walkthrough:
     *
     * ["/a","/a/b","/c/d","/c/d/e","/c"]
     * After sort: ["/a", "/a/b", "/c", "/c/d", "/c/d/e"]
     *
     * Steps:
     * 1. Add "/a" to ans
     * 2. "/a/b" starts with "/a/" -> skip
     * 3. "/c" doesn't start with "/a/" -> add to ans
     * 4. "/c/d" starts with "/c/" -> skip
     * 5. "/c/d/e" starts with "/c/" -> skip
     *
     * Result: ["/a", "/c"]
     * The key insight is that by sorting first, we ensure parent folders are processed before their subfolders, allowing us to skip all subfolders of a parent folder we've already added.
     *
     * @param folder
     * @return
     */
    public static List<String> removeSubfolders1(String[] folder) {
        // Sort the folders lexicographically so parent folders come before their subfolders
        Arrays.sort(folder);

        // Initialize result list with the first folder
        List<String> ans = new ArrayList<>();
        ans.add(folder[0]);

        // Iterate through remaining folders starting from index 1
        for (int i = 1; i < folder.length; i++) {
            // Get the last added folder path and add a trailing slash
            String lastFolder = ans.get(ans.size() - 1) + "/";

            // Check if current folder starts with lastFolder
            // If it doesn't start with lastFolder, then it's not a subfolder
            if (!folder[i].startsWith(lastFolder)) {
                ans.add(folder[i]);
            }
        }

        return ans;
    }

}
