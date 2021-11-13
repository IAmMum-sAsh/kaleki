package ru.mirea.kaleki.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company extends BaseEntity{
    protected String name;

    @OneToOne
    protected User CEO;
    @Column(columnDefinition = "TEXT")
    protected String address;

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", CEO=" + CEO +
                ", address='" + address + '\'' +
                '}';
    }
}