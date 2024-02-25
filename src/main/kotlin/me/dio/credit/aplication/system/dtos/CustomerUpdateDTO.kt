package me.dio.credit.aplication.system.dtos

import java.math.BigDecimal

data class CustomerUpdateDTO(
        val firstName: String,
        val lastName: String,
        val income: BigDecimal,
        val zipCode: String,
        val street: String
) {

}
