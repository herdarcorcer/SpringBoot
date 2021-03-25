package com.ejemploRestApi.RestApiExample.business

import com.ejemploRestApi.RestApiExample.model.Persona

interface IPersonaBusiness {

    fun List(): List<Persona>
    fun Load(idPersona:Long): Persona
    fun save(persona:Persona): Persona
    fun remove(idPersona: Long)
}