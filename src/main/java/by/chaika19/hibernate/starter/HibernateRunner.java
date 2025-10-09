package by.chaika19.hibernate.starter;

import by.chaika19.hibernate.starter.entity.*;
import by.chaika19.hibernate.starter.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        Company company = Company.builder()
                .name("Yan2")
                .build();

        User user = User.builder()
                .username("ShanyaStalker@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Vovan")
                        .lastname("Vovanovich")
                        .birthDate(new BirthDate(LocalDate.of(2005, 10, 22)))
                        .build())
                .role(Role.ADMIN)
                .build();
//        log.info("User object in transient state {} ", user);

        User user2 = null;
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

//                session.saveOrUpdate(company);
                session.saveOrUpdate(user);
//                user2 = session.get(User.class, 2);
//                System.out.println(user2);
//                System.out.println(user2.getCompany().getName());

                session.getTransaction().commit();
            }
        } catch (Exception e) {
            log.error("Exception occurred: ", e);
            throw e;
        }
    }
}
