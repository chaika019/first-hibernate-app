package by.chaika19.hibernate.starter.converter;

import by.chaika19.hibernate.starter.entity.BirthDate;
import jakarta.persistence.AttributeConverter;

import java.sql.Date;
import java.util.Optional;

public class BirthdayConverter implements AttributeConverter<BirthDate, Date> {
    @Override
    public Date convertToDatabaseColumn(BirthDate birthDay) {
        return Optional.ofNullable(birthDay)
                .map(BirthDate::birthDate)
                .map(Date::valueOf)
                .orElse(null);
    }

    @Override
    public BirthDate convertToEntityAttribute(Date date) {
        return Optional.ofNullable(date)
                .map(Date::toLocalDate)
                .map(BirthDate::new)
                .orElse(null);
    }
}
