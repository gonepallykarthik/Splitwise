package com.scaler.Splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity(name = "users_group" )

public class Group extends BaseModel {
    private String groupName;
    private Date createdOn;
    @ManyToOne
    private User admin;
    @OneToMany(mappedBy = "group")
    List<Expense> expenses;
    @ManyToMany
    List<User> users;
}
