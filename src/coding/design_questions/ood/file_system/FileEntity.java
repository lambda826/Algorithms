/**
 *  @author Yunxiang He
 *  @date 04/09/2019
 */

package coding.design_questions.ood.file_system;

import java.time.LocalDateTime;

public abstract class FileEntity {

    protected String name;
    protected int size;
    protected final LocalDateTime createOn;
    protected LocalDateTime lastModified;

    public FileEntity(String name) {
        this.name = name;
        this.createOn = LocalDateTime.now();
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public LocalDateTime getCreateOn() {
        return createOn;
    }

}
