package jpabook.jpashop.domain.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    //엔티티메니저 주입
    private final EntityManager em;

    /**
     * 상품 저장
     */
    public void save(Item item){
        if(item.getId() == null){
            em.persist(item); //persist 전 까지 id 없음.
        }else {
            em.merge(item); //merge : update 비슷,
        }
    }

    /**
     *  상품 한 개 조회
     */
    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    /**
     *  상품 리스트 조회, JPQL사용
     */
    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }

}