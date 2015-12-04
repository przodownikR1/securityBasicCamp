package pl.java.scalatech.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item extends PKEntity{

    private static final long serialVersionUID = -7065958217839138809L;
    private String name;
    private BigDecimal price;
    private boolean enable;
}
