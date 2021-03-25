package com.ejemploRestApi.RestApiExample.web

import com.ejemploRestApi.RestApiExample.business.PersonaBusiness
import com.ejemploRestApi.RestApiExample.exception.BusinessException
import com.ejemploRestApi.RestApiExample.exception.NotFoundException
import com.ejemploRestApi.RestApiExample.model.Persona
import com.ejemploRestApi.RestApiExample.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception

@RestController // mapear los datos que estan el base de datos con nuestro servidor... y enviarlos por la respuesta de la pagina web para retornar json
@CrossOrigin(origins = ["http://localhost:4200"])
@RequestMapping(Constants.URL_BASE_PERSONAS)
class PersonaRestController {
    @Autowired
    val personaBusiness: PersonaBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Persona>>{
        return try{
           ResponseEntity(personaBusiness!!.List(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun load(@PathVariable("id") idPersona:Long): ResponseEntity<Any>{
        return try{
            ResponseEntity(personaBusiness!!.Load(idPersona), HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("")
    fun insert(@RequestBody persona: Persona): ResponseEntity<Any>{
        return try{
            personaBusiness!!.save(persona)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_PERSONAS + "/" + persona.id)
            ResponseEntity(responseHeader, HttpStatus.CREATED)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody persona: Persona): ResponseEntity<Any>{
        return try {
            personaBusiness!!.save(persona)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") idPersona: Long): ResponseEntity<Any>{
        return try {
            personaBusiness!!.remove(idPersona)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


}