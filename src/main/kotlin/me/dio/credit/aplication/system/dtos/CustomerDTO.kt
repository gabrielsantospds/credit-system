package me.dio.credit.aplication.system.dtos

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.credit.aplication.system.entity.Address
import me.dio.credit.aplication.system.entity.Customer
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDTO(
        @field:NotEmpty(message = "Invalid data") val firstName: String,
        @field:NotEmpty(message = "Invalid data") val lastName: String,
        @field:NotEmpty(message = "Invalid data") @field:CPF(message = "Invalid CPF") val cpf: String,
        @field:NotNull(message = "Invalid data") val income: BigDecimal,
        @field:NotEmpty(message = "Invalid data") @field:Email(message = "Invalid email") val email: String,
        @field:NotEmpty(message = "Invalid data") val password: String,
        @field:NotEmpty(message = "Invalid data") val zipCode: String,
        @field:NotEmpty(message = "Invalid data") val street: String
) {
        fun toEntity(): Customer {
                return Customer(
                        firstName = this.firstName,
                        lastName = this.lastName,
                        cpf = this.cpf,
                        income = this.income,
                        email = this.email,
                        password = this.password,
                        address = Address(
                                zipCode = this.zipCode,
                                street = this.street
                        )
                )
        }
}
