package sorzzzzy.dearmydog.domain.Item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("F")
public class Food extends Item {

    private String allowance;
    private String ingredient;
}
