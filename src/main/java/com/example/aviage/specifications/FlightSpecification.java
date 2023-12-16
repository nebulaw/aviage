package com.example.aviage.specifications;

import com.example.aviage.entities.City;
import com.example.aviage.entities.Flight;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

public class FlightSpecification {

    public static Specification<Flight> idNotNull() {
        return (root, query, builder) -> builder.isNotNull(root.get("ID"));
    }

    public static Specification<Flight> sourceCityEquals(City sourceCity) {
        return (root, query, builder) -> builder.equal(root.get("sourceCity"), sourceCity);
    }

    public static Specification<Flight> destinationCityEquals(City destinationCity) {
        return (root, query, builder) -> builder.equal(root.get("destinationCity"), destinationCity);
    }

    public static Specification<Flight> flightMonthEquals(Date date) {
        return (root, query, builder) -> {
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return builder.between(root.get("flightDate"),
                    YearMonth.from(localDate).atDay(1), YearMonth.from(localDate).atEndOfMonth());
        };
    }

    public static Specification<Flight> sourceAndDestinationCityEquals(City sourceCity, City destinationCity) {
        return Specification.where(idNotNull()).and(sourceCityEquals(sourceCity))
                .and(destinationCityEquals(destinationCity));
    }

}
