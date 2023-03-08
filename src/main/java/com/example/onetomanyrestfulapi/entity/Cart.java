package com.example.onetomanyrestfulapi.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "cart")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToMany(mappedBy = "cart",  cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Không sử dụng trong toString()
    @JsonManagedReference
    // chú ý biến cart này được khai báo trong Class Item bên dưới. Chúng phải giống y chang nhau cái tên
    private Set<Item> items;

    /* sử dụng collection là Set vì muốn tập hợp sản phẩm không được trùng lặp nhau */
}
