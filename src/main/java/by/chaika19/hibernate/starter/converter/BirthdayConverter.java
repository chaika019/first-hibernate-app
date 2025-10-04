package by.chaika19.hibernate.starter.converter;

import by.chaika19.hibernate.starter.entity.BirthDay;
import jakarta.persistence.AttributeConverter;

import java.sql.Date;
import java.util.Optional;

public class BirthdayConverter implements AttributeConverter<BirthDay, Date> {
    @Override
    public Date convertToDatabaseColumn(BirthDay birthDay) {
        return Optional.ofNullable(birthDay)
                .map(BirthDay::birthDate)
                .map(Date::valueOf)
                .orElse(null);
    }

    @Override
    public BirthDay convertToEntityAttribute(Date date) {
        return Optional.ofNullable(date)
                .map(Date::toLocalDate)
                .map(BirthDay::new)
                .orElse(null);
    }
}
