/**
 * @author Yunxiang He
 * @date 04/09/2019
 */

package questions.design_questions.ood.file_system;

public enum Type {

    FOLDER("File folder"), PDF("pdf"), TXT("txt"), DOC("doc"), JPG("jpg");

    private final String description;

    Type(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
