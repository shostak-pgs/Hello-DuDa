package app.controller;

import app.dao.UserDto;
import app.entity.Order;
import app.exception.ServiceException;
import app.service.impl.OrderServiceImpl;
import app.service.impl.UserServiceImpl;
import app.utils.validator.UserValidatorImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/app/users")
public class UserController {

    private UserServiceImpl userService;
    private OrderServiceImpl orderService;

    public UserController(UserServiceImpl userService, OrderServiceImpl orderService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    /**
     * Returns the list of all users
     * @return {@link ResponseEntity}
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getUsers() {
        return userService.getAllUsers();
    }

    /**
     * Returns the user by its name.
     *  @param id - the id of the user
     * @return {@link ResponseEntity}
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER') ")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUser(@NotBlank(message = "The id cannot be blank") @PathVariable(value = "id") final Long id) throws ServiceException {
        UserDto user = userService.getUser(id);
        Order order = orderService.get(String.valueOf(user.getId()));
        user.setOrder(order);
        addSessionAttributes(user, order);
        return ResponseEntity.ok(user);
    }

    /**
     * Creates the user.
     * //@param userDtoFields - the user's name and password in JSON format
     * @return {@link ResponseEntity}
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody final UserDto userDto) {
        new UserValidatorImpl().validate(userDto);

        final URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/" + userDto.getId())
                        .buildAndExpand(userService.create(userDto))
                        .toUri();

        return ResponseEntity.created(uri).build();
    }

    /**
     * Deletes user by its id.
     * @param id - the name of the user
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private void addSessionAttributes(UserDto user, Order order) {

       // HttpSession session =
      //  session.setAttribute(ID, user.getId());
       // session.setAttribute(ORDER_ID, order.getId());
      //  session.setAttribute(NAME, user.getLogin());
    }
}

