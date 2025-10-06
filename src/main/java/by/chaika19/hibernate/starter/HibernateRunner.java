package by.chaika19.hibernate.starter;

import by.chaika19.hibernate.starter.converter.BirthdayConverter;
import by.chaika19.hibernate.starter.entity.BirthDay;
import by.chaika19.hibernate.starter.entity.Role;
import by.chaika19.hibernate.starter.entity.User;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAttributeConverter(new BirthdayConverter(), true);
//        configuration.addAnnotatedClass(User.class);
//        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession();) {
            session.beginTransaction();

            User user = User.builder()
                    .username("a4@gmail.com")
                    .firstname("Ivan")
                    .lastname("Ivanovich")
                    .birthDay(
                            new BirthDay(LocalDate.of(2001, 9, 11)))
                    .role(Role.ADMIN)
                    .build();
//            session.save(user);
//            session.update(user);
//            session.saveOrUpdate(user);
//            session.delete(user);
            User user1 = session.get(User.class, "a2@gmail.com");
            user1.setFirstname("Bayan");
//            session.flush();
//            session.clear();
//            session.evict(user1);
            System.out.println(user1);
            session.getTransaction().commit();
        }
    }
}
