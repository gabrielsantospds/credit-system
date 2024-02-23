package me.dio.credit.aplication.system.repositories

import me.dio.credit.aplication.system.entity.Credit
import me.dio.credit.aplication.system.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CreditRepository: JpaRepository<Credit, Long> {
}