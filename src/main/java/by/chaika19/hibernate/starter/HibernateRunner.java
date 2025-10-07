package by.chaika19.hibernate.starter;

import by.chaika19.hibernate.starter.entity.BirthDate;
import by.chaika19.hibernate.starter.entity.PersonalInfo;
import by.chaika19.hibernate.starter.entity.Role;
import by.chaika19.hibernate.starter.entity.User;
import by.chaika19.hibernate.starter.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        User user = User.builder()
                .username("vovan228@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Vovan")
                        .lastname("Vovanovich")
                        .birthDate(new BirthDate(LocalDate.of(2005, 10, 22)))
                        .build())
                .role(Role.ADMIN)
                .build();
        log.info("User object in transient state {} ", user);

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                session.merge(user);

                session.getTransaction().commit();
            }
        } catch (Exception e) {
            log.error("Exception occurred: ", e);
            throw e;
        }
    }
}
