package com.netcracker.validate;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class Validate {

    private DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-uuuu");

    public boolean correctDate(String date) {
        try {
            LocalDate start = LocalDate.parse(date, f);
            return true;
        } catch (Exception ex) {
            return false;
        }


    }

    public boolean dates(String startDate, String endDate) {

        try {
            LocalDate start = LocalDate.parse(startDate, f);

            LocalDate stop = LocalDate.parse(endDate, f);

            LocalDate today = LocalDate.now();

            return start.isBefore(stop) && (start.isAfter(today) || start.equals(today));

        } catch (Exception ex) {
            return false;
        }
    }

    public boolean correctPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("(\\+)\\d{12}");
    }
}
