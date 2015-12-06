package pl.java.scalatech.web.common;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.annotation.CurrentUser;
import pl.java.scalatech.entity.AbstactId;
import pl.java.scalatech.entity.User;

@Slf4j
public abstract class AbstractRestRepoController<T extends AbstactId> {

    private final @NonNull JpaRepository<T, Long> repo;

    public AbstractRestRepoController(JpaRepository<T, Long> repository) {
        this.repo = repository;
    }
    protected abstract String getUrl();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<T> getAll(@CurrentUser User user) {
        List<T> result =  repo.findAll();
        if(result == null || result.isEmpty()) { //TODO some problem with empty collection !!!!
            result  =null;
        }
        log.info("+++++    NULL -> EMPTY        {}  ->  {}, result  {}", user,this.getClass().getSimpleName(),result);
        return result;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<T>  getObjectId(@PathVariable("id") T t) {
        return ResponseEntity.ok(t);
    }

    @RequestMapping(value = { "", "/{id}" }, method = RequestMethod.POST)
    public ResponseEntity<Void> create(@Valid T t, BindingResult result, Errors errors,UriComponentsBuilder uriBuilder) {
        log.info("+++  {} save :  {}", t.getClass().getSimpleName(), t);
        if (result.hasErrors()) {
            log.info("+++  object:{} error  {}", t.getClass().getSimpleName(), result);
            return null;
        }
        T loaded = repo.save(t);
        UriComponents uriComponents = uriBuilder.path("/api/"+getUrl()+"/{id}").buildAndExpand(loaded.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);

    }

    @SneakyThrows
    @RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        T t = repo.findOne(id);
        repo.delete(t);
        return (ResponseEntity<?>) ResponseEntity.noContent();
    }
}
