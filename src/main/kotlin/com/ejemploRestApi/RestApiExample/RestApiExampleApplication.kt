package com.ejemploRestApi.RestApiExample

import com.ejemploRestApi.RestApiExample.dao.PersonaRepository
import com.ejemploRestApi.RestApiExample.model.Persona
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SpringBootApplication
class RestApiExampleApplication:CommandLineRunner{

	@Autowired
	val personaRepository: PersonaRepository? = null
	override fun run(vararg args: String?) {

		val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
		var persona1 = Persona(dni = 123213123, nombre ="Hernan", apellido = "Cortes", fechaNac = LocalDate.parse("27-04-1994", formatter))

		personaRepository!!.save(persona1)
	}
}

fun main(args: Array<String>) {
	runApplication<RestApiExampleApplication>(*args)
}
