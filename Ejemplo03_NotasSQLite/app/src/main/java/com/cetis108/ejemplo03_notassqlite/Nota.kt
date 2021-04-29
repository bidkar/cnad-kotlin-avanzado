package com.cetis108.ejemplo03_notassqlite

import java.io.Serializable

class Nota: Serializable {
    var Id: Int? = null
    var Titulo: String? = null
    var Descripcion: String? = null

    constructor(id: Int, titulo: String, descripcion: String) {
        this.Id = id
        this.Titulo = titulo
        this.Descripcion = descripcion
    }
}