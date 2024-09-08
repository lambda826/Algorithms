/**
 * @author Yunxiang He
 * @date 03/23/2019
 */

package questions.design_questions.ood.library_management_system.myDemo;

import questions.design_questions.ood.library_management_system.myDemo.domain.Book;
import questions.design_questions.ood.library_management_system.myDemo.domain.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum DB {

    DB;
    private final Set<Book> books = new HashSet<>();
    private final Set<User> users = new HashSet<>();
    private final Map<User, Set<Book>> user_book = new HashMap<>();
    private final Map<Book, User> book_user = new HashMap<>();

    public Set<Book> getBooks() {
        return books;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Map<User, Set<Book>> getUser_book() {
        return user_book;
    }

    public Map<Book, User> getBook_user() {
        return book_user;
    }

}
