/**
 * @author Yunxiang He
 * @date 04/09/2019
 */

package questions.design_questions.ood.file_system;

public class File extends FileEntity {

    private String content;
    private Directory holder;

    public File(String name) {
        super(name);
    }

    public File(String name, String content, int size, Directory holder) {
        this(name);
        this.holder = holder;
        this.content = content;
        this.size = size;
    }

    public Directory getHolder() {
        return holder;
    }

    public void setHolder(Directory holder) {
        this.holder = holder;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
