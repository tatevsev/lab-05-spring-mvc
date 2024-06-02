package com.cydeo.controller;

import com.cydeo.model.Profile;
import com.cydeo.service.CartService;
import com.cydeo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
public class LabController {

    public final CartService cartService;
    public final ProductService productService;


    public LabController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping("/lab")
    public String labList(Model model){

        model.addAttribute("firstLab","lab-01-Coupling");
        model.addAttribute("secondLab","lab-02-IoC");
        model.addAttribute("thirdLab","lab-03-DI");
        model.addAttribute("fourthLab","lab-04-Spring Boot");
        model.addAttribute("fifthLab","lab-05-Spring MVC");

        return "lab/lab-list";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        Profile profile = new Profile();

        profile.setName("John");
        profile.setSurname("Doe");
        profile.setUserName("johndoe");
        profile.setPhoneNumber("0123456789");
        profile.setEmail("johndoe@cydeo.com");
        profile.setCreatedDate(LocalDateTime.now());


        model.addAttribute("profile",profile);

        return "profile/profile-info";


    }


    @GetMapping("/login/{email}/{phoneNumber}")
    public String login(@PathVariable("email") String email,@PathVariable("phoneNumber") String phoneNumber,Model model){

        model.addAttribute("email",email);
        model.addAttribute("phoneNumber",phoneNumber);
        model.addAttribute("loginMessage","Login successful");

        return "login/login-info";
    }


    @GetMapping("/cart-list")
    public String cartList(Model model){

        model.addAttribute("cartList",cartService.retrieveCartList());
        return "cart/cart-list";
    }


    @GetMapping("/cart-list/{cartId}")
    public String getCardItemList(Model model, @PathVariable UUID cartId){

        model.addAttribute("cartItemList",cartService.retrieveCartDetail(cartId));


        return "cart/cart-detail";
    }


    @GetMapping("/search-product/{name}")
    public String searchProduct(Model model, @PathVariable String name){

        model.addAttribute("productList",productService.searchProduct(name));

        return "product/product-list";
    }




}
