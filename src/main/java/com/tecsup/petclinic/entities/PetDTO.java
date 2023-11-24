package com.tecsup.petclinic.entities;


import java.sql.Date;

public class PetDTO {
    private String name;
    private int typeId;
    private int ownerId;
    private Date birth_date;

    public PetDTO() {
    }

    public PetDTO( String name, int typeId, int ownerId, Date birth_date) {
        this.name = name;
        this.typeId = typeId;
        this.ownerId = ownerId;
        this.birth_date = birth_date;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    @Override
    public String toString() {
        return "PetDTO{" +
                "name='" + name + '\'' +
                ", typeId=" + typeId +
                ", ownerId=" + ownerId +
                ", birth_date=" + birth_date +
                '}';
    }
}
