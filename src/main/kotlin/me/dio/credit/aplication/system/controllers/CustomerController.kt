package me.dio.credit.aplication.system.controllers

import me.dio.credit.aplication.system.dtos.CustomerDTO
import me.dio.credit.aplication.system.dtos.CustomerView
import me.dio.credit.aplication.system.entity.Customer
import me.dio.credit.aplication.system.services.impl.CustomerService
import org.springframework.beans.BeanUtils
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customers")
class CustomerController(
        private val customerService: CustomerService
) {

    @PostMapping
    fun postCustomer(@RequestBody customerDTO: CustomerDTO): String {
        val customer = Customer()
        BeanUtils.copyProperties(customerDTO, customer)
        customerService.save(customer)
        return "Customer saved successfully"
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Long): CustomerView {
        val customer: Customer = this.customerService.findById(id)
        val customerView = CustomerView()
        BeanUtils.copyProperties(customer, customerView)
        return customerView
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long) = this.customerService.delete(id)
}