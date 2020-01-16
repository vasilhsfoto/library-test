package com.vassilis.library.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDate;

@Service
@RequestScope
@Data
public class AuthorDetails {
    private final String name;
    private final LocalDate birthDate;
}
