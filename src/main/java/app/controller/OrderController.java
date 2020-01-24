package app.controller;

import app.entity.Good;
import app.exception.ServiceException;
import app.service.impl.OrderGoodsServiceImpl;
import app.utils.validator.OrderValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/app/orders")
public class OrderController {

    private OrderGoodsServiceImpl service;
    private OrderValidatorImpl validator;

    @Autowired
    public OrderController(OrderGoodsServiceImpl service, OrderValidatorImpl validator) {
        this.service = service;
        this.validator = validator;
    }

    /**
     * Returns the order by its id.
     *  @param id - the id of the order
     * @return {@link ResponseEntity}
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Good> getUser(@NotBlank(message = "The id cannot be blank") @PathVariable(value = "id") final Long id,
                              final HttpServletResponse response) throws ServiceException {

        List<Good> goods = service.getGoods(id);
        response.setStatus(HttpServletResponse.SC_OK);
        return goods;
    }


    /**
     * Handles {@link HttpServlet} PUT Method.
     * Adds the selected good to the order
     * @param chosenItem  the good to add to the order
     * @param id  the id of current basket
     * @throws IOException  thrown when occur exception in getting Writer object
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    protected List<Good> addGood(@RequestBody final Good chosenItem,
                                 @PathVariable(value = "id") final Long id,
                                 final HttpServletResponse response) {
        validator.validate(chosenItem);
        service.add(chosenItem, id);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return service.getGoods(id);
    }

    /**
     * Deletes order by its id.
     * @param id - the name of the user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
       // orderService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
