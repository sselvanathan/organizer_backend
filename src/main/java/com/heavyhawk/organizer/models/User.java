package com.heavyhawk.organizer.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String phone_number;
    private String token;
    private String firebase_user_id;
    private LocalDateTime last_login_at;
    private LocalDateTime registered_at;
}
