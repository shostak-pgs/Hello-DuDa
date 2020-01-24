package app.controller;

import app.entity.Good;
import app.exception.ServiceException;
import app.service.impl.GoodsServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/app/goods")
public class GoodController {

    private GoodsServiceImpl service;

    @Inject
    public GoodController(GoodsServiceImpl service) {
        this.service = service;
    }

    /**
     * Returns the list of all users
     * @return {@link ResponseEntity}
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Good>> getGoods() throws SQLException {
        return ResponseEntity.ok(service.getGoods());
    }

    /**
     * Returns the good by its id.
     *  @param id - the id of the good
     * @return {@link ResponseEntity}
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Good> getGood(@NotBlank(message = "The id cannot be blank") @PathVariable(value = "id") final long id) throws ServiceException {
        //Good good = service.getGood(id);
        return ResponseEntity.ok(new Good());//
    }

    /**
     * Creates the good.
     * @param good - the {@link Good}
     * @return {@link ResponseEntity}
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createGood(@RequestBody final Good good) throws SQLException, IOException {
        final URI uri = null;
              //  ServletUriComponentsBuilder.fromCurrentRequest()
                        //.path("/" + good.getId())
                       // .buildAndExpand(service.create(good))
                        //.toUri();

        return ResponseEntity.created(uri).build();
    }

    /**
     * Deletes good by its id.
     * @param id - the id of the good
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") final long id) throws SQLException {
       // service.delete(id);
        return ResponseEntity.noContent().build();
    }
}