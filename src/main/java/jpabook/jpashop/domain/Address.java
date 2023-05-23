package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
//Setter는 제거함. 생성 할 때만 생성하고 변경 불가능하게 ㄱㄱ
@Embeddable //둘중에 하나만 있어도됨 근데 그냥 둘다 써주자
public class Address {

    // @Embeddable 은 원래 public or protected로 써준다.
    // protected로 되어서 막혀있으니까 new 생성자로 그냥 생성하지 않고, public Address (~~) 이거를 쓰게씀

    //값 테이블은 첫 생성 때 그냥 고정되어 져서 변경이 불가능해야 함
    private String city;
    private String street;
    private String zipCode;

    protected Address() { //public은 많이 생성 할테니까 protected 해 놓으면 함부로 new로 생성하면 안되겠네~~ 라는 생각을 하게 됨

    }
    public Address(String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }


}
