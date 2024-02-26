package me.dio.credit.aplication.system.services.impl

import me.dio.credit.aplication.system.entity.Credit
import me.dio.credit.aplication.system.repositories.CreditRepository
import me.dio.credit.aplication.system.services.ICreditService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService(
        private val creditRepository: CreditRepository,
        private val customerService: CustomerService
) : ICreditService {

    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> = this.creditRepository.findAllByCustomer(customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit: Credit = creditRepository.findByCreditCode(creditCode)
                ?: throw RuntimeException("Credit code $creditCode not found")
        return if(credit.customer?.id == customerId) credit else throw IllegalArgumentException("Contact admin")
    }

}