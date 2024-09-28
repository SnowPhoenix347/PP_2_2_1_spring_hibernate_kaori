package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      List<User> usersList = new ArrayList<>();
      List<Car> cars = new ArrayList<>();
      usersList.add(new User("User1", "Lastname1", "user1@mail.ru"));
      usersList.add(new User("User2", "Lastname2", "user2@mail.ru"));
      usersList.add(new User("User3", "Lastname3", "user3@mail.ru"));
      usersList.add(new User("User4", "Lastname4", "user4@mail.ru"));
      cars.add(new Car("Suzuki", 11));
      cars.add(new Car("Mazda", 22));
      cars.add(new Car("Toyota", 33));
      cars.add(new Car("Djiguli", 44));

      for (int i = 0; i < usersList.size(); i++) {
         cars.get(i).setUser(usersList.get(i));
         usersList.get(i).setCar(cars.get(i));
         userService.add(usersList.get(i));
      }

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car: " + user.getCar().toString());
         System.out.println();
      }

      System.out.println(userService.getUserByCar("Mazda", 22));

      context.close();
   }
}
