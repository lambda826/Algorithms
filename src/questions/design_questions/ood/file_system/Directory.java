/**
 * @author Yunxiang He
 * @date 04/09/2019
 */

package questions.design_questions.ood.file_system;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Directory extends FileEntity {

    protected List<FileEntity> contents;

    public Directory(String name) {
        super(name);
        this.size = 0;
        this.contents = new ArrayList<>();
    }

    public boolean add(FileEntity entry) {
        this.size += entry.getSize();
        this.lastModified = LocalDateTime.now();
        return contents.add(entry);
    }

    public boolean delete(FileEntity entry) {
        this.size -= entry.size;
        this.lastModified = LocalDateTime.now();
        return contents.remove(entry);
    }

    // other methods
    public List<File> getAllFiles(Directory dir, Predicate<File> p) {
        if (dir == null) {
            return null;
        } else {
            List<File> files = new ArrayList<>();
            for (FileEntity entry : dir.getContents()) {
                if (entry instanceof File && p.test((File) entry)) {
                    files.add((File) entry);
                } else {
                    files.addAll(getAllFiles((Directory) entry, p));
                }
            }
            return files;
        }
    }

    public List<FileEntity> getContents() {
        return contents;
    }

    public void setContents(List<FileEntity> contents) {
        this.contents = contents;
    }
}
