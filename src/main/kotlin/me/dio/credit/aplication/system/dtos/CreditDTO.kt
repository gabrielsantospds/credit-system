package me.dio.credit.aplication.system.dtos

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import me.dio.credit.aplication.system.entity.Credit
import me.dio.credit.aplication.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDTO(
    @field:NotNull(message = "Invalid data") val creditValue: BigDecimal,
    @field:Future(message = "Date must be in the future") val dayFirstInstallment: LocalDate,
    val numberOfInstallment: Int,
    @field:NotNull(message = "Invalid data") val customerId: Long
){

    fun toEntity() = Credit(
        creditValue =  this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallment = this.numberOfInstallment,
        customer = Customer(id = this.customerId)
    )
}
