package paramau2


import grails.rest.*
import grails.converters.*

class MauController {
	static responseFormats = ['json', 'xml']
	
    def index() { }

    def respuesta(){
        def data = [exito: true, mensaje: "Hola Mau!"]

        render data as JSON
        return
    }
}
