package ru.mirea.kaleki.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The type Company.
 */
@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company extends BaseEntity{
    /**
     * The Name.
     */
    protected String name;

    /**
     * The Ceo.
     */
    @OneToOne
    protected User CEO;
    /**
     * The Address.
     */
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