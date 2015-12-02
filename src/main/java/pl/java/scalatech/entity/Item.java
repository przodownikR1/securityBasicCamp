package pl.java.scalatech.entity;

import static javax.persistence.GenerationType.AUTO;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item  {

    @Id
    @GeneratedValue(strategy=AUTO)
    private Long id;
    private String name;
    private BigDecimal price;
    private boolean enable;
}
