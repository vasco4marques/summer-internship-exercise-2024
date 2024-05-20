package com.premiumminds.internship.teknonymy;

import java.time.LocalDateTime;

public record Person(String name, Character sex, Person[] children, LocalDateTime dateOfBirth) {
}
