package org.example.practice.organization.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
public class OrganizationDtoForListIn {
    @NotBlank
    private String name;
    private String inn;
    private String active;

    public OrganizationDtoForListIn() {
    }

    public OrganizationDtoForListIn(String name, String active) {
        this.name = name;
        this.active = active;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String isActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "OrganizationDtoForListIn{" +
                "inn='" + inn + '\'' +
                ", name='" + name + '\'' +
                ", active=" + active +
                '}';
    }
}
