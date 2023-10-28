package pcShop.view;

import pcShop.model.Product;
import pcShop.model.RoleName;
import pcShop.model.User;
import pcShop.service.UserService;
import pcShop.util.DataBase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        Set<RoleName> set = new HashSet<>();
        Set<RoleName> set2 = new HashSet<>();
        set2.add(RoleName.USER);
        set2.add(RoleName.ADMIN);
        set.add(RoleName.USER);
        User user = new User(2, "linh", "linh1", "1", "ninhbinh", "0925581932", true, set);
        User admin = new User();
        admin.setId(1);
        admin.setStatus(true);
        admin.setUsername("admin1");
        admin.setPassword("admin1");
        admin.setRoles(set2);
        userService.save(admin);
        userService.save(user);
        DataBase<User> data = new DataBase<>();
        for (User u : data.readFromFile(DataBase.USER_PATH)) {
            System.out.println("-------------------------------------------");
            System.out.println(u);
        }
    }
}
