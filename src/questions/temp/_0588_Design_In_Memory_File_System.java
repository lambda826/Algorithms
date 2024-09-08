/**
 * @author Yunxiang He
 * @date 04/09/2019
 */

package questions.temp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

Design an in-memory file system to simulate the following functions:
    ls: 
        Given a path in string format. If it is a file path, return a list that only contains this file's name. 
        If it is a directory path, return the list of file and directory names in this directory. 
        Your output (file and directory names together) should in lexicographic order.
    mkdir: 
        Given a directory path that does not exist, you should make a new directory according to the path. 
        If the middle directories in the path don't exist either, you should create them as well. 
        This function has void return type.
    addContentToFile: 
        Given a file path and file content in string format. 
        If the file doesn't exist, you need to create that file containing given content. 
        If the file already exists, you need to append given content to original content. 
        This function has void return type.
    readContentFromFile: 
       Given a file path, return its content in string format.


Note:
    You can assume all file or directory paths are absolute paths which begin with / and do not end with / except that the path is just "/".
    You can assume that all operations will be passed valid parameters and users will not attempt to retrieve file content or list a directory or file that does not exist.
    You can assume that all directory names and file names only contain lower-case letters, and same names won't exist in the same directory.

*/

public class _0588_Design_In_Memory_File_System {

    public static void main(String[] args) {
        _0588_Design_In_Memory_File_System test = new _0588_Design_In_Memory_File_System();
        test.ls("/");
        test.mkdir("/goowmfn");
        test.mkdir("/z");
        test.addContentToFile("/goowmfn/c", "shetopcy");
        System.out.println(test.ls("/goowmfn/c"));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private final FileEntity root;

    public _0588_Design_In_Memory_File_System() {
        root = new FileEntity();
    }

    public List<String> ls(String path) {
        List<String> list = new ArrayList<>();
        FileEntity temp = getPath(path);
        if (temp.isFile) {
            list.add(path.substring(path.lastIndexOf("/") + 1));
        } else {
            list.addAll(temp.sub.keySet());
            if (temp.content != null) {
                list.add(temp.content);
            }
            Collections.sort(list);
        }
        return list;
    }

    public void mkdir(String path) {
        getPath(path);
    }

    public void addContentToFile(String filePath, String content) {
        FileEntity temp = getPath(filePath);
        temp.isFile = true;
        if (temp.content == null) {
            temp.content = content;
        } else {
            temp.content += content;
        }
    }

    public String readContentFromFile(String filePath) {
        return getPath(filePath).content;
    }

    private FileEntity getPath(String filePath) {
        FileEntity temp = root;
        filePath = filePath.substring(filePath.indexOf("/") + 1);
        if (filePath != null && !filePath.isEmpty()) {
            for (String p : filePath.split("/")) {
                temp.sub.putIfAbsent(p, new FileEntity());
                temp = temp.sub.get(p);
            }
        }
        return temp;
    }

    class FileEntity {
        boolean isFile = false;
        Map<String, FileEntity> sub = new HashMap<>();
        String content = null;
    }

}
