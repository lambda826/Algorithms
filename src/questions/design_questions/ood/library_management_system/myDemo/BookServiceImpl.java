/**
 * @author Yunxiang He
 * @date 03/23/2019
 */

package questions.design_questions.ood.library_management_system.myDemo;

import questions.design_questions.ood.library_management_system.myDemo.domain.Book;
import questions.design_questions.ood.library_management_system.myDemo.domain.EnumBookStatus;
import questions.design_questions.ood.library_management_system.myDemo.domain.User;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    @Override
    public Book getBookByID(String id) {
        for (Book b : DB.DB.getBooks()) {
            if (b.getId().equals(id)) {
                return b;
            }
        }
        return null;
    }

    @Override
    public List<Book> getBooksByBookInfo(Book book) {
        List<Book> books = new ArrayList<>();
        return books;
    }

    @Override
    public void addBook(Book book) {
        DB.DB.getBooks().add(book);
    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public void deleteBook(Book book) {
        DB.DB.getBooks().remove(book);

    }

    @Override
    public void chechIn(User user, Book book) {
        book.setStatus(EnumBookStatus.AVAILABLE);
        DB.DB.getUser_book().get(user).remove(book);
        DB.DB.getBook_user().remove(book);
    }

    @Override
    public void checkOout(User user, Book book) {
        book.setStatus(EnumBookStatus.LOANED);
        DB.DB.getUser_book().get(user).add(book);
        DB.DB.getBook_user().put(book, user);

    }

    @Override
    public void renewBook(User user, Book book) {
        book.setStatus(EnumBookStatus.LOANED);
    }

}
