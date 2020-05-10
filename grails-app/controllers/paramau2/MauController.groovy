package paramau2


import grails.rest.*
import grails.converters.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import static org.apache.poi.ss.usermodel.Cell.*
import java.io.File

class MauController {
    static responseFormats = ['json', 'xml']

    def index() {}

    def respuesta() {
        def data = [exito: true, mensaje: "Hola Mau!"]

        render data as JSON
        return
    }

    def uploadFile() {
        def data = [:]
        try {
            // get excel file from response
            def file = request.getFile('file');
            if (file) {
                // create a workbook from file
                def workbook = new XSSFWorkbook(file.getInputStream())
                // Select to page (sheet) to get rows
                def sheet = workbook.getSheetAt(0)
                // Delete the first row to don't get headers
                sheet.removeRow(sheet.getRow(0))
                // get each row from the sheet
                def values = []
                for (row in sheet.rowIterator()) {
                    def value = [:]
                    value.curp = row.getCell(0).getStringCellValue()
                    value.nombre = row.getCell(1).getStringCellValue()
                    value.apellido = row.getCell(2).getStringCellValue()
                    value.edad = row.getCell(3).getStringCellValue()
                    values.add(value)
                }
                data = [exito: true, mensaje: "Hola Mau!", rows: values ]
            } else {
                data = [exito: false, mensaje: "No file"]
            }
            render data as JSON
        } catch (Exception e) {
            e.printStackTrace()
            data = [exito: false, mensaje: "Error"]
            render data as JSON
        }
    }
}
