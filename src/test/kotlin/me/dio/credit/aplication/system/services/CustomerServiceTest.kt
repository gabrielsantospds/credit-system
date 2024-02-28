package me.dio.credit.aplication.system.services

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import me.dio.credit.aplication.system.entity.Address
import me.dio.credit.aplication.system.entity.Customer
import me.dio.credit.aplication.system.repositories.CustomerRepository
import me.dio.credit.aplication.system.services.impl.CustomerService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerServiceTest {

    @MockK lateinit var customerRepository: CustomerRepository
    @InjectMockKs lateinit var customerService: CustomerService

    @Test
    fun `must create a customer`() {
        //given
        val fakeCustomer: Customer = buildCustomer()
        every { customerRepository.save(any()) } returns fakeCustomer
        //when
        val customerSaved: Customer = customerService.save(fakeCustomer)
        //then
        Assertions.assertThat(customerSaved).isNotNull
        Assertions.assertThat(customerSaved).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.save(fakeCustomer) }
    }

    @Test
    fun `must find a customer by Id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
        //when
        val returnedCustomer = customerService.findById(fakeId)
        //then
        Assertions.assertThat(returnedCustomer).isNotNull
        Assertions.assertThat(returnedCustomer).isSameAs(fakeCustomer)
        Assertions.assertThat(returnedCustomer).isExactlyInstanceOf(Customer::class.java)
        verify(exactly = 1) { customerRepository.findById(fakeId) }
    }

    @Test
    fun `must not find a Customer by Id and must throw a RuntimeException`() {
        //given
        val fakeId: Long = Random().nextLong()
        every { customerRepository.findById(fakeId) } returns Optional.empty()
        //when
        //then
        Assertions.assertThatExceptionOfType(RuntimeException::class.java)
            .isThrownBy { customerService.findById(fakeId) }
            .withMessage("Id $fakeId not found")
        verify(exactly = 1) { customerRepository.findById(fakeId) }
    }

    @Test
    fun `must delete a Customer by Id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
        every { customerRepository.delete(fakeCustomer) } just runs
        //when
        customerService.delete(fakeId)
        //then
        verify(exactly = 1) { customerRepository.findById(fakeId) }
        verify(exactly = 1) { customerRepository.delete(fakeCustomer) }
    }

    private fun buildCustomer(
       firstName: String = "Junior",
       lastName: String = "Francklin",
       cpf: String = "28475934625",
       email: String = "junior@gmail.com",
       password: String = "123456",
       zipCode: String = "123456",
       street: String = "Rua Junior",
       income: BigDecimal = BigDecimal.valueOf(1000.0),
       id: Long = 1L
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        address = Address(
            zipCode = zipCode,
            street = street
        ),
        income = income,
        id = id
    )
}