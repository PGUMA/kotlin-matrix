package lab.pguma.tuple

infix fun <A, B, C> Pair<A, B>.then(that: C): Triple<A, B, C> = Triple(this.first, this.second, that)

fun <R, C, V> matrixOf(vararg data: Triple<R, C, V>): Matrix<R, C, V> = ImmutableMatrix(*data)

private class MatrixCell<R, C, out V>(
    val matrix: Pair<R, C>,
    val value: V,
) {
    fun isMatchTo(matrix: Pair<R, C>): Boolean {
        return this.matrix == matrix
    }

    fun getCellValue(): V {
        return value
    }
}

interface Matrix<R, C, out V> {
    fun get(row: R, column: C): V?
    fun get(matrix: Pair<R, C>): V?
}

private class ImmutableMatrix<R, C, out V> constructor (vararg data: Triple<R, C, V>) : Matrix<R, C, V>{

    private val cells: List<MatrixCell<R, C, V>>

    init {
        cells = listOf(*data).map { it.toMatrixCell() }
    }

    override fun get(row: R, column: C): V? {
        return get(row to column)
    }

    override fun get(matrix: Pair<R, C>): V? {
        return cells.find { it.isMatchTo(matrix) }?.getCellValue()
    }
}

private fun <R, C, V> Triple<R, C, V>.toMatrixCell(): MatrixCell<R, C, V> = MatrixCell(this.first to this.second, this.third)