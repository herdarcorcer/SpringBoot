package com.ejemploRestApi.RestApiExample.business

import com.ejemploRestApi.RestApiExample.dao.PersonaRepository
import com.ejemploRestApi.RestApiExample.exception.BusinessException
import com.ejemploRestApi.RestApiExample.exception.NotFoundException
import com.ejemploRestApi.RestApiExample.model.Persona
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class PersonaBusiness: IPersonaBusiness {

    @Autowired // instanciar para usar la interfaz
    val personaRepository: PersonaRepository? = null

    @Throws(BusinessException::class)
    override fun List(): List<Persona> {

        try{
            return personaRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun Load(idPersona: Long): Persona {
        val op: Optional<Persona>

        try {
            op = personaRepository!!.findById(idPersona)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if(!op.isPresent){
            throw NotFoundException("No se encontro la persona con id $idPersona")
        }

        return op.get()
    }

    @Throws(BusinessException::class)
    override fun save(persona: Persona): Persona {
        try {
            return personaRepository!!.save(persona)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun remove(idPersona: Long) {
        val op : Optional<Persona>

        try {
            op = personaRepository!!.findById(idPersona)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if(!op.isPresent){
            throw NotFoundException("No se encontro la persona con el id $idPersona")
        }else{
            try {
                personaRepository!!.deleteById(idPersona)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }
}