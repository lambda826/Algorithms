/**
 * @author Yunxiang He
 * @date 04/09/2019
 */

package questions.design_questions.ood.file_system;

import java.time.LocalDateTime;

public class Context {

    public static void main(String[] args) {
        Directory directory = new Directory("test");
        directory.getAllFiles(directory, (a) -> a.getSize() > 10);
        directory.getAllFiles(directory, (a) -> a.getSize() > 10 && a.getCreateOn().isBefore(LocalDateTime.now()));
    }
}
