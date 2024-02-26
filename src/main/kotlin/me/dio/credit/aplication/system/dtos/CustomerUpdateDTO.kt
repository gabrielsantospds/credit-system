package me.dio.credit.aplication.system.dtos

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.credit.aplication.system.entity.Customer
import java.math.BigDecimal

data class CustomerUpdateDTO(
        @field:NotEmpty(message = "Invalid data") val firstName: String,
        @field:NotEmpty(message = "Invalid data") val lastName: String,
        @field:NotNull(message = "Invalid data") val income: BigDecimal,
        @field:NotEmpty(message = "Invalid data") val zipCode: String,
        @field:NotEmpty(message = "Invalid data") val street: String
) {
        fun toEntity(customer: Customer): Customer {
                customer.firstName = this.firstName
                customer.lastName = this.lastName
                customer.income = this.income
                customer.address.street = this.street
                customer.address.zipCode = this.zipCode
                return customer
        }
}
