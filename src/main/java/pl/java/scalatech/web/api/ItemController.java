package pl.java.scalatech.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.java.scalatech.entity.Item;
import pl.java.scalatech.repository.ItemRepository;
import pl.java.scalatech.web.common.AbstractRestRepoController;
@RestController
@RequestMapping("/api/item")
public class ItemController extends AbstractRestRepoController<Item>{


    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    public ItemController(JpaRepository<Item, Long> repository) {
        super(repository);
        this.itemRepository = (ItemRepository) repository;
    }

    @Override
    protected String getUrl() {
        return "item";
    }

}
