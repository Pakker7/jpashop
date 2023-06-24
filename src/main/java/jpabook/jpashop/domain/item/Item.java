package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.domain.OrderItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// 상속 구조를 단일 테이블로 매핑하고자 할 때 사용됩니다.
// 이 전략을 사용하면 부모 클래스와 자식 클래스의 모든 속성을 하나의 테이블로 통합하여 매핑합니다.
// 테이블에는 부모 클래스와 자식 클래스의 모든 속성이 포함되며, 부모 클래스의 식별자(primary key) 컬럼을 사용하여 각 레코드를 식별합니다.
// 자식 클래스의 속성 중에는 부모 클래스에만 존재하는 경우 null로 저장됩니다.

@DiscriminatorColumn(name = "dtype")
// 단일 테이블로 매핑된 상속 구조에서 자식 클래스를 식별하기 위한 컬럼을 지정합니다.
// 이 컬럼에는 자식 클래스의 구분 값을 저장합니다. 구분 값을 사용하여 해당 레코드가 어떤 자식 클래스의 인스턴스인지 식별합니다.
// name 속성은 데이터베이스 테이블의 컬럼 이름을 지정합니다.
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String itemName;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public void removeStock(int count) {
        this.stockQuantity -= count;
    }
}


//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) 속성들
//InheritanceType.SINGLE_TABLE (기본값):
// 상속 구조를 하나의 테이블로 매핑합니다.
// 부모 클래스와 자식 클래스의 모든 속성을 하나의 테이블로 통합하여 매핑하며, @DiscriminatorColumn을 사용하여 자식 클래스를 구분합니다.
// 한테이블에 다 때려박기
//
//InheritanceType.JOINED:
// 상속 구조를 각각의 테이블로 매핑합니다. 각각의 클래스에 대해 별도의 테이블이 생성되며,
// 부모 클래스의 테이블과 자식 클래스의 테이블을 <<조인>>하여 필요한 정보를 가져옵니다.
// JOIN 연산을 사용하여 데이터를 조회하므로 효율적인 쿼리 실행 계획을 만들 수 있습니다.
//
//InheritanceType.TABLE_PER_CLASS:
// 상속 구조를 각각의 테이블로 매핑합니다. 각각의 클래스에 대해 별도의 테이블이 생성되며,
// 부모 클래스의 테이블에 있는 공통 속성을 자식 클래스의 테이블에 중복해서 저장합니다.
// 이 방식은 자식 클래스마다 테이블을 생성하여 자식 클래스의 속성을 저장하므로 테이블 간의 조인이 필요하지 않습니다.


//@DiscriminatorColumn
// 어노테이션은 상속 구조를 매핑할 때 사용되며, 자식 클래스를 식별하기 위한 컬럼을 지정합니다.
//name: 자식 클래스를 구분하는 컬럼의 이름을 지정합니다. 기본값은 "DTYPE"입니다.
//columnDefinition: 컬럼의 데이터 타입과 추가 설정을 지정할 수 있습니다. 예를 들어, "VARCHAR(255) DEFAULT 'DEFAULT_VALUE'"와 같이 컬럼의 데이터 타입과 기본값을 설정할 수 있습니다.
//length: 컬럼의 길이를 지정합니다. 기본값은 31입니다.
//discriminatorType: 컬럼의 데이터 타입을 지정합니다. 기본값은 DiscriminatorType.STRING으로 문자열로 지정됩니다. 다른 옵션으로는 DiscriminatorType.INTEGER 등이 있습니다.
//columnDefinition과 length는 데이터베이스 종류에 따라 다르게 적용될 수 있으므로, 데이터베이스와의 호환성을 고려하여 사용해야 합니다.