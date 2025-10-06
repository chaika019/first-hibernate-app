package by.chaika19.hibernate.starter.entity;

import by.chaika19.hibernate.starter.converter.BirthdayConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    private String username;
    private String firstname;
    private String lastname;
    @Convert(converter = BirthdayConverter.class)
    @Column(name = "birth_day")
    private BirthDate birthDate;
    @Enumerated(EnumType.STRING)
    private Role role;
}
