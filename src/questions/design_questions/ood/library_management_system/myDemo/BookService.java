/**
 *  @author Yunxiang He
 *  @date 03/23/2019
 */

package questions.design_questions.ood.library_management_system.myDemo;

import questions.design_questions.ood.library_management_system.myDemo.domain.Book;
import questions.design_questions.ood.library_management_system.myDemo.domain.User;

import java.util.List;

public interface BookService {

    Book getBookByID(String id);

    List<Book> getBooksByBookInfo(Book book);

    void addBook(Book book);

    void updateBook(Book book);

    void deleteBook(Book book);

    void renewBook(User user, Book book);

    void chechIn(User user, Book book);

    void checkOout(User user, Book book);
}
