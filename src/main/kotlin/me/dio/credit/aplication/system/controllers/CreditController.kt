package me.dio.credit.aplication.system.controllers

import jakarta.validation.Valid
import me.dio.credit.aplication.system.dtos.CreditDTO
import me.dio.credit.aplication.system.dtos.CreditView
import me.dio.credit.aplication.system.dtos.CreditViewList
import me.dio.credit.aplication.system.entity.Credit
import me.dio.credit.aplication.system.services.impl.CreditService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditController(
    private val creditService: CreditService
) {

    @PostMapping
    fun saveCredit(@RequestBody @Valid creditDTO: CreditDTO): ResponseEntity<String> {
        val creditSaved = this.creditService.save(creditDTO.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Credit ${creditSaved.creditCode} - ${creditSaved.customer?.firstName} saved")
    }

    @GetMapping
    fun getAllCredits(@RequestParam(value = "customerId") customerId: Long): ResponseEntity<List<CreditViewList>> {
        val creditViewList =
            this.creditService.findAllByCustomer(customerId).stream().map { credit: Credit -> CreditViewList(credit) }
                .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(creditViewList)
    }

    @GetMapping("/{creditCode}")
    fun getCreditById(
        @PathVariable creditCode: UUID,
        @RequestParam(value = "customerId") customerId: Long
    ): ResponseEntity<CreditView> {
        val credit = this.creditService.findByCreditCode(customerId, creditCode)
        return ResponseEntity.status(HttpStatus.OK).body(CreditView(credit))
    }
}