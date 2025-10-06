package by.chaika19.hibernate.starter;

import by.chaika19.hibernate.starter.entity.BirthDate;
import by.chaika19.hibernate.starter.entity.Role;
import by.chaika19.hibernate.starter.entity.User;
import by.chaika19.hibernate.starter.util.HibertnateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
        User user = User.builder()
                .username("a52@gmail.com")
                .firstname("Ivan")
                .lastname("Ivanovich")
                .birthDate(new BirthDate(LocalDate.of(2005, 10, 22)))
                .role(Role.ADMIN)
                .build();

        try (SessionFactory sessionFactory = HibertnateUtil.buildSessionFactory()) {
            try (Session session1 = sessionFactory.openSession()) {
                session1.beginTransaction();

                session1.saveOrUpdate(user);

                user.setFirstname("Pasha");
                System.out.println(session1.isDirty());

                session1.getTransaction().commit();
            }
//            try (Session session2 = sessionFactory.openSession()) {
//                session2.beginTransaction();
//                session2.delete(user);
//                session2.getTransaction().commit();
//            }
        }
    }
}
