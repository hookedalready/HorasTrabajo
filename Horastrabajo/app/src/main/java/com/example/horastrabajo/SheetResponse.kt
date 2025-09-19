package com.example.horastrabajo

data class SheetResponse(val table: Table)
data class Table(val rows: List<Row>)
data class Row(val c: List<Cell>)
data class Cell(val v: String?, val f: String?)