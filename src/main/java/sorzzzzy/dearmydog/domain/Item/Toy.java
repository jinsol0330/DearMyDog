package sorzzzzy.dearmydog.domain.Item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("T")
public class Toy extends Item{

    private String component;
    private String size;
}
