package org.example.demo.Web;

import org.example.demo.Service.CustomerService;
import org.example.demo.dto.Projection;
import org.example.demo.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // 1. View the homepage with the list of customers
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listCustomers", customerService.getAllCustomers());
        return "index";  // This refers to index.html in the templates directory
    }

    // 2. Show the form to add a new customer
    @GetMapping("/showNewCustomerForm")
    public String showNewCustomerForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "new_customer";
    }

    // 3. Save the new customer after form submission
    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/";
    }

    // 4. Show the projected investment based on customer details
    @GetMapping("/projectedInvestment/{id}")
    public String showProjectedInvestment(@PathVariable("id") long id, Model model) {
        Customer customer = customerService.getCustomerById(id);

        // Determine the interest rate based on the savings type
        double interestRate = customer.getSavingsType().equalsIgnoreCase("Savings-Deluxe") ? 0.15 : 0.10;

        // Calculate the projections (without saving to the database)
        List<Projection> projections = new ArrayList<>();
        double startingAmount = customer.getInitialDeposit();

        for (int year = 1; year <= customer.getYears(); year++) {
            double interest = startingAmount * interestRate;
            double endingBalance = startingAmount + interest;

            // Formatting values as currency
            String formattedStartingAmount = String.format("%.2f", startingAmount);
            String formattedInterest = String.format("%.2f", interest);
            String formattedEndingBalance = String.format("%.2f", endingBalance);

            projections.add(new Projection(year, formattedStartingAmount, formattedInterest, formattedEndingBalance));
            startingAmount = endingBalance;
        }

        model.addAttribute("customer", customer);
        model.addAttribute("projections", projections);
        return "projected_investment";  // This refers to projected_investment.html in the templates directory
    }

    // 5. Handle form submission from the projected investment page (e.g., to return to the homepage)
    @PostMapping("/submitInvestment")
    public String submitInvestment() {
        return "redirect:/";  // Redirects to the homepage
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "update_customer";
    }

    @PostMapping("/updateCustomer/{id}")
    public String updateCustomer(@PathVariable("id") Long id, @ModelAttribute("customer") Customer customer) {
        // Fetch the existing customer from the database
        Customer existingCustomer = customerService.getCustomerById(id);

        // Update the customer details
        existingCustomer.setCustomerNumber(customer.getCustomerNumber());
        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setInitialDeposit(customer.getInitialDeposit());
        existingCustomer.setYears(customer.getYears());
        existingCustomer.setSavingsType(customer.getSavingsType());

        // Save the updated customer
        customerService.saveCustomer(existingCustomer);

        return "redirect:/";
    }



    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable(value = "id") long id) {
        customerService.deleteCustomerById(id);
        return "redirect:/";
    }

}
