package com.company;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Vacancy {
    private int id;
    private String companyName;
    private String positionName;
    private String positionSalary;
    private String positionDuties;
    private String positionConditions;
    private String positionRequirements;
    private double positionRate;
    private String createdAt;
    private String workType;
    private String workExperience;
    private String phone;
    private String email;
    private String region;
    private String district;


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setPositionRequirements(String positionRequirements) {
        this.positionRequirements = positionRequirements;
    }
}
