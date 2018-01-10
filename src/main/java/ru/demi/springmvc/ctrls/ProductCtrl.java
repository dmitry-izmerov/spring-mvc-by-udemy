package ru.demi.springmvc.ctrls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.demi.springmvc.models.Product;
import ru.demi.springmvc.services.ProductService;

@Controller
public class ProductCtrl {

	@Autowired
	private ProductService productService;

	@RequestMapping("/products")
	public String getAllProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@RequestMapping("/product/{id}")
	public String getProduct(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "product";
	}

	@RequestMapping("/product")
	public String getEmptyProduct(Model model) {
		model.addAttribute("product", new Product());
		return "product";
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String saveProduct(Product product) {
		productService.saveProduct(product);
		return "redirect:/products";
	}

	@RequestMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable Integer id) {
		productService.deleteProduct(id);
		return "redirect:/products";
	}
}
