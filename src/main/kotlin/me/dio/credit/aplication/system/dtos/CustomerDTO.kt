package me.dio.credit.aplication.system.dtos

import java.math.BigDecimal

data class CustomerDTO(
        val firstName: String,
        val lastName: String,
        val cpf: String,
        val income: BigDecimal,
        val email: String,
        val password: String,
        val zipCode: String,
        val street: String
) {


}
