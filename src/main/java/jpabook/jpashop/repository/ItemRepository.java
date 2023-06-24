package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemRepository {

    //상품 등록
    //상품 목록 조회
    //상품 수정

    private final EntityManager em;

    @Transactional
    public Long save(Item item){
        if(item.getId()==null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
        return item.getId();
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i ",Item.class)
                .getResultList();
    }

}
