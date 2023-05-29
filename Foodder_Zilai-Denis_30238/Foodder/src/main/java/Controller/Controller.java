package Controller;

import Controller.dto.LoginRequestDTO;
import Domain.Order;
import Domain.User;
import Persistence.Generic.AccountRepo;
import Persistence.Generic.UserRepo;
import Persistence.Hbm.AccountHBMRepo;
import Persistence.Hbm.OrderHBMRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@CrossOrigin
public class Controller {
    @Autowired
    UserRepo userRepo;
    @Autowired
    AccountHBMRepo accountRepo;
    @Autowired
    OrderHBMRepo orderRepo;
    //http://localhost:8080/users
    @RequestMapping(value = "/users", method = RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<?> VerifyLogin(@RequestBody LoginRequestDTO dto) {
    //        System.out.println(dto.getEmail()+"  "+dto.getPassword());
        System.out.println("=============== Test =============");
        User logged = userRepo.FindByLoginCredentials(dto.getEmail(), dto.getPassword());
        if (logged == null)
            return new ResponseEntity<String>("User not found!", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<User>(logged, HttpStatus.OK);
    }

    //http://localhost:8080/accounts/3 HTTP DELETE
    @RequestMapping(value = "/accounts/{id}",method = RequestMethod.DELETE,consumes = "application/json")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id){
        try {
            this.accountRepo.Remove(id);
            return new ResponseEntity<String>("DONE!", HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<String>("Error deleting account", HttpStatus.NOT_FOUND);
        }
    }


    //http://localhost:8080/orders
//    {
//     orderNo:2
//     price:5
//    quantity:20
//    status: true
//    }
    @RequestMapping(value = "/orders",method = RequestMethod.POST,consumes = "application/json")
    public ResponseEntity<?> addOrder(@RequestBody Order order){
        try{
            this.orderRepo.Save(order);
            return new ResponseEntity<String>("DONE!", HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<String>("Error adding order", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/orders",method = RequestMethod.PUT,consumes = "application/json")
    public ResponseEntity<?> updateOrder(@RequestBody Order order){
        try{
            this.orderRepo.Update(order.getId(),order);
            return new ResponseEntity<String>("DONE!", HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<String>("Error adding order", HttpStatus.NOT_FOUND);
        }
    }
}