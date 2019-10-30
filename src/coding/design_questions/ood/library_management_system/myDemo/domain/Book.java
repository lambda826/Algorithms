/**
 *  @author Yunxiang He
 *  @date 03/23/2019
 */

package coding.design_questions.ood.library_management_system.myDemo.domain;

public class Book {

    private String id;
    private String bookName;
    private String author;
    private EnumBookStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public EnumBookStatus getStatus() {
        return status;
    }

    public void setStatus(EnumBookStatus status) {
        this.status = status;
    }

}
