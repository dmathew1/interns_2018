package rahul_jenn.v2_ORM;

import rahul_jenn.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * Created by denze on 7/15/2018.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aws_hibernate");
        EntityManager em = emf.createEntityManager();
        String[] row;
        String firstName;
        String lastName;

        Scanner sc = new Scanner(new File("C:\\Users\\denze\\Downloads\\java8_stuff\\src\\main\\resources\\sample_users.csv"));
        while(sc.hasNextLine()){
            row = sc.nextLine().split(",");
            firstName = row[0];
            lastName = row[1];
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        }

        Query query = em.createQuery("from User");
        List<User> list = (List<User>)query.getResultList();
        for(User user : list){
            System.out.println(user.toString());
        }
    }
}
