package me.dio.credit.aplication.system.services

import me.dio.credit.aplication.system.entity.Customer

interface ICustomerService {
    fun save(customer: Customer): Customer

    fun findById(customerId: Long): Customer

    fun delete(customerId: Long)
}