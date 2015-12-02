package pl.java.scalatech;

import java.math.BigDecimal;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.tags.form.RadioButtonsTag;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.entity.Item;
import pl.java.scalatech.repository.ItemRepository;

@SpringBootApplication
@Slf4j
public class SpringSecurityBasicApplication implements CommandLineRunner {

    @Autowired
    private ItemRepository itemRepo;
    private Random random = new Random();
    
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityBasicApplication.class, args);
    }

    
    @Override
    public void run(String... args) throws Exception {
        for(int i =0;i<20;i++){
            Item item = Item.builder().enable(random.nextBoolean()).name("item"+i).price(BigDecimal.valueOf(random.nextInt(10)*i)).build();
            Item loaded = itemRepo.save(item);
            log.info("+++  loaded : {}",loaded);
        }
        
    }
}
