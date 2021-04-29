package com.cetis108.ejemplo02_gridviewcars

class Coche {
    var imagen: Int? = null
    var titulo: String? = null
    var descripcion: String? = null
    var precio: String? = null
    var venta: Boolean? = null

    constructor(imagen: Int, titulo: String, descripcion: String, precio: String, venta: Boolean) {
        this.imagen = imagen
        this.titulo = titulo
        this.descripcion = descripcion
        this.precio = precio
        this.venta = venta
    }
}