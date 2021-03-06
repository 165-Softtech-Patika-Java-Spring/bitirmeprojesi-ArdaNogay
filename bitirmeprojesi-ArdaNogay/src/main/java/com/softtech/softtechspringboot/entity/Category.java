package com.softtech.softtechspringboot.entity;

import com.softtech.softtechspringboot.entity.BaseEntity.BaseEntity;
import com.softtech.softtechspringboot.Enum.CategoryType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "CATEGORY")
public class Category extends BaseEntity {

    @Id
    @SequenceGenerator(name = "Category",sequenceName = "CATEGORY_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "Category")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY_TYPE" ,nullable = false ,updatable = false)
    private CategoryType categoryType;

    @Column(name = "TAX" ,nullable = false ,updatable = true)
    private BigDecimal tax;

}
