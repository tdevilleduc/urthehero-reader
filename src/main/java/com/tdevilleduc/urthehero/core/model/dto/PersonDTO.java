package com.tdevilleduc.urthehero.core.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PersonDTO {

    private Integer id;
    private String login;
    private String displayName;
    private String email;
    private String password;

    public PersonDTO(String login, String displayName, String email, String password) {
        this.login = login;
        this.displayName = displayName;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "login='" + login + '\'' +
                ", displayName='" + displayName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
