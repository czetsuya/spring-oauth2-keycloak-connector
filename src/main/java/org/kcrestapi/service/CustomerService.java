package org.kcrestapi.service;

import org.kcrestapi.model.CustomerModel;
import org.kcrestapi.dto.Customer;
import org.kcrestapi.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired CustomerRepository customerRepository;

    public Customer createCustomer(final Customer customer) {

        CustomerModel customerModel = new CustomerModel();
        BeanUtils.copyProperties(customer, customerModel);
        customerModel = customerRepository.save(customerModel);
        Customer customerData = new Customer();
        BeanUtils.copyProperties(customerModel, customerData);

        return customerData;
    }

    public List<Customer> getCustomers() {
        List<CustomerModel> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);

        List<Customer> customerList = new ArrayList<>();
        for (CustomerModel customerModel : customers) {
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerModel, customer);
            customerList.add(customer);
        }

        return customerList;
    }

    public Customer getCustomer(Long id) {

        Optional<CustomerModel> customer = customerRepository.findById(id);
        Customer customerData = new Customer();
        BeanUtils.copyProperties(customer.get(), customerData);
        return customerData;
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
