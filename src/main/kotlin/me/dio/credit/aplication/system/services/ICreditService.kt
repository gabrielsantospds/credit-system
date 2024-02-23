package me.dio.credit.aplication.system.services

import me.dio.credit.aplication.system.entity.Credit
import java.util.*

interface ICreditService {
    fun save(credit: Credit): Credit

    fun findAllByCustomer(customerId: Long): List<Credit>

    fun findByCreditCode(creditCode: UUID): Credit
}