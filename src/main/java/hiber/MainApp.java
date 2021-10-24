package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      /* Создайте несколько пользователей с машинами, добавьте их в базу данных, вытащите обратно. */
      User user5 = new User("Chaoxiang", "Zhang","zhang@mail.ch");
      user5.setCar(new Car("Baojun", 45));

      User user6 = new User("Donghai", "Chen","chen@mail.ch");
      user6.setCar(new Car("Changan", 56));

      userService.add(user5);
      userService.add(user6);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         if (!Objects.isNull(user.getCar())) {
            System.out.printf("Car: model \"%s\", series \"%d\"%n",
                    user.getCar().getModel(), user.getCar().getSeries());
         }
         System.out.println();
      }

      context.close();
   }
}
