package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable //둘중에 하나만 있어도됨 근데 그냥 둘다 써주자
public class Address {

    private String street;
    private String zipCode;


}
