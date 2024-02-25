package me.dio.credit.aplication.system.repositories

import me.dio.credit.aplication.system.entity.Credit
import me.dio.credit.aplication.system.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface CreditRepository: JpaRepository<Credit, Long> {
    fun findByCreditCode(creditCode: UUID): Credit?

    @Query(value = "SELECT * FROM credit WHERE customer_id = ?1", nativeQuery = true)
    fun findAllByCustomer(customerId: Long): List<Credit>

}