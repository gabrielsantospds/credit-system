package me.dio.credit.aplication.system.services.impl

import me.dio.credit.aplication.system.entity.Customer
import me.dio.credit.aplication.system.repositories.CustomerRepository
import me.dio.credit.aplication.system.services.ICustomerService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService {
    override fun save(customer: Customer): Customer = this.customerRepository.save(customer)

    override fun findById(customerId: Long): Customer = this.customerRepository.findById(customerId).orElseThrow{
        throw RuntimeException("Id $customerId not found")
    }

    override fun delete(customerId: Long) = this.customerRepository.deleteById(customerId)
}
