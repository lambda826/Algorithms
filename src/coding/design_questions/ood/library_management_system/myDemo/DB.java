/**
 *  @author Yunxiang He
 *  @date 03/23/2019
 */

package coding.design_questions.ood.library_management_system.myDemo;

import coding.design_questions.ood.library_management_system.myDemo.domain.Book;
import coding.design_questions.ood.library_management_system.myDemo.domain.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum DB {

    DB;
    private Set<Book> books = new HashSet<>();
    private Set<User> users = new HashSet<>();
    private Map<User, Set<Book>> user_book = new HashMap<>();
    private Map<Book, User> book_user = new HashMap<>();

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
