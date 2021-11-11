/**
 *  @author Yunxiang He
 *  @date 03/23/2019
 */

package questions.design_questions.ood.library_management_system.myDemo;

import questions.design_questions.ood.library_management_system.myDemo.domain.User;

public interface UserService {

    User getUserById(String id);

    void addUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

}
