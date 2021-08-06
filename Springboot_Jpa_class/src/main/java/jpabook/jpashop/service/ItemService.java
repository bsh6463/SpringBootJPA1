package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    /**
     * ItemService는 단순히 ItemRepository에 위임하는 로직
     * 단순히 위임만 하는 로직이 필요할까..?
     */

    //repository 주입
    private final ItemRepository itemRepository;

    /**
     * Item 저장, @Transactional 별도 지정.
     */
    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    /**
     * 엔티티 업데이트
     */
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){
        Item findItem = itemRepository.findOne(itemId); //영속상태 엔티티 찾아와서
        findItem.setName(name);   //영속상태 엔티티를 변경함.
        findItem.setStockQuantity(stockQuantity);        //영속상태이므로 dirtycheking 대상임.

    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long id){
        return itemRepository.findOne(id);
    }

}
