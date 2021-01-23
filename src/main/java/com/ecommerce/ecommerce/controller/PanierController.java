package com.ecommerce.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import com.ecommerce.ecommerce.dao.UserRepository;
import com.ecommerce.ecommerce.domaine.CheckoutVo;
import com.ecommerce.ecommerce.domaine.ProduitVo;
import com.ecommerce.ecommerce.domaine.UserVo;
import com.ecommerce.ecommerce.exception.NotEnoughProductsInStockException;
import com.ecommerce.ecommerce.model.Produit;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.service.ICheckout;
import com.ecommerce.ecommerce.service.IProduit;
import com.ecommerce.ecommerce.service.ShoppingCartService;

@Controller
public class PanierController {

	 private final ShoppingCartService shoppingCartService;

	  private final IProduit productService;
	  
	  private final ICheckout chekoutService;
	  
	  private final UserRepository userRepo;

	    @Autowired
	    public PanierController(ShoppingCartService shoppingCartService, IProduit productService,ICheckout chekoutService,
	    		UserRepository userRepo) {
	        this.shoppingCartService = shoppingCartService;
	        this.productService = productService;
	        this.chekoutService = chekoutService;
	        this.userRepo = userRepo;
	        }

	    @GetMapping("/shoppingCart")
	    public ModelAndView shoppingCart() {
	        ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("panier");

	        modelAndView.addObject("products", shoppingCartService.getProductsInCart());
	        modelAndView.addObject("total",5);
	        return modelAndView;
	    }

	    @GetMapping("/shoppingCart/addProduct/{idProduit}")
	    public String addProductToCart(@PathVariable("idProduit") Long idProduit) {
	       productService.findById(idProduit).ifPresent(shoppingCartService::addProduct);
	        
	      
	        return "redirect:/shoppingCart";
	    }

	    @GetMapping("/shoppingCart/removeProduct/{idProduit}")
	    public String removeProductFromCart(@PathVariable("idProduit") Long idProduit) {
	        productService.findById(idProduit).ifPresent(shoppingCartService::removeProduct);
	        return "redirect:/shoppingCart";
	    }

	 /*   @GetMapping("/shoppingCart/checkout")
	    public ModelAndView checkout() {
	      /*  try {
	            shoppingCartService.checkout();
	        } catch (NotEnoughProductsInStockException e) {
	            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
	        }
	        return shoppingCart();
	    	
	    	ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("checkout");
			
			return  modelAndView;
	    }*/
	    
	

	    @RequestMapping(value = "/shoppingCart/checkout", method = RequestMethod.POST)
		public String checkout(@AuthenticationPrincipal UserDetails currentUser) throws NotEnoughProductsInStockException  {
	    	
	    	//User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    	
	    	//Use auth = (UserVo) SecurityContextHolder.getContext().getAuthentication();
	    	
	    	//UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    	
			/*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User customUser = (User)auth.getPrincipal();
			
			String email = customUser.getEmail();*/
	        User user = (User) userRepo.findByUsername(currentUser.getUsername());

	    	 shoppingCartService.checkout(user);
	    	
	    
			return "redirect:/produits";


		}
	    
	    
}
