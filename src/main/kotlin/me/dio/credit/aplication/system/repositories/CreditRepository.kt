package me.dio.credit.aplication.system.repositories

import me.dio.credit.aplication.system.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository

interface CreditRepository: JpaRepository<Credit, Long> {
}