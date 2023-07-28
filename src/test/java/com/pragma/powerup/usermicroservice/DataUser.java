package com.pragma.powerup.usermicroservice;

import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;

import java.time.LocalDate;

public class DataUser {
    public static final long ADMIN_ROLE_ID = 1L;
    public static final long CUSTOMER_ROLE_ID = 2L;
    public static final long OWNER_ROLE_ID = 3L;
    public static final long EMPLOYEE_ROLE_ID = 4L;
    public static Role ADMIN_ROLE = new Role(ADMIN_ROLE_ID, null, null);
    public static Role CUSTOMER_ROLE = new Role(CUSTOMER_ROLE_ID,null, null);
    public static Role OWNER_ROLE = new Role(OWNER_ROLE_ID, null, null);
    public static Role EMPLOYEE_ROLE = new Role(EMPLOYEE_ROLE_ID, null, null);
    public static final User USER_WITHOUT_ROLE = new User(1L, "Juan", "Pérez", "12345678", "123456789", LocalDate.of(1990, 1, 1), "juan@mail.com", "password1", null);
    public static final User USER = new User(1L, "Juan", "Pérez", "12345678", "123456789", LocalDate.of(1990, 1, 1), "juan@mail.com", "password1", CUSTOMER_ROLE);
    public static final User USER_WITH_ADMIN_ROLE = new User(1L, "Juan", "Pérez", "12345678", "123456789", LocalDate.of(1990, 1, 1), "juan@mail.com", "password1", ADMIN_ROLE);
    public static final User USER_WITH_CUSTOMER_ROLE = new User(2L, "María", "Gómez", "23456789", "234567890", LocalDate.of(1991, 2, 2), "maria@mail.com", "password2", CUSTOMER_ROLE);
    public static final User USER_WITH_OWNER_ROLE = new User(3L, "Carlos", "Rodríguez", "34567890", "345678901", LocalDate.of(1992, 3, 3), "carlos@mail.com", "password3", OWNER_ROLE);
    public static final User USER_WITH_EMPLOYEE_ROLE = new User(4L, "Ana", "López", "45678901", "456789012", LocalDate.of(1993, 4, 4), "ana@mail.com", "password4", EMPLOYEE_ROLE);

}
