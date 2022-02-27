package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Владимир", "Епифанцев", (byte) 50);
        userService.saveUser("Сергей", "Пахомов", (byte) 55);
        userService.saveUser("Александр", "Маслаев", (byte) 65);
        userService.saveUser("Анатолий", "Осмоловский", (byte) 52);
        for(User user: userService.getAllUsers()) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();



    }
}
