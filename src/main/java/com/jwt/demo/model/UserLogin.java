package com.jwt.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@Entity
@Table(name = "USERLOGIN")
public class UserLogin {

    @Id
    @Column
    private int id;
    @Column
    private String userName;
    @Column
    private String passWord;
    @Column
    private String email;
}