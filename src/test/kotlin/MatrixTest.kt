import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import lab.pguma.tuple.matrixOf
import lab.pguma.tuple.then

class MatrixTest: StringSpec() {
    init {
        "Matrixの生成" {
            matrixOf(
                "row" to "column" then "value"
            )
        }

        "Matrixから存在する定義の値が取得できること" {
            val matrix = matrixOf(
                "row_1" to "column_1" then "value_1",
                "row_2" to "column_2" then "value_2",
                "row_3" to "column_3" then "value_3",
                "row_4" to "column_4" then "value_4",
            )
            matrix.get("row_3" to "column_3") shouldBe "value_3"
        }

        "Matrixから存在しない定義の値はnullが返ること" {
            val matrix = matrixOf(
                "row_1" to "column_1" then "value_1",
                "row_2" to "column_2" then "value_2",
                "row_3" to "column_3" then "value_3",
                "row_4" to "column_4" then "value_4",
            )
            matrix.get("row_5" to "column_3") shouldBe null
        }
    }
}