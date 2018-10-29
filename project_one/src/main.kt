import java.util.*

fun main(args: Array<String>) {
    val kotcoin = Blockchain

    for (i in 1..15000) {
        kotcoin.addNewBlock(Block(i, kotcoin.latestBlock.hash, Date().time))
        val index = (1..15000).random()
        /**
         * Força um block alterado no lugar do ultimo
         */
        if (i == index) {
            kotcoin.chain[i] = Block(i, kotcoin.chain[i - 2].hash, Date().time)

            println(
                    """Data: ${kotcoin.latestBlock.data}
                    |isValid?: ${kotcoin.isValid(kotcoin)}
                    |""".trimMargin()
            )
        }

    }

    for (block in kotcoin.chain) println(
            """Data: ${block.data}
            |Previous hash: ${block.previousHash}
            |Current hash: ${block.hash}
            |isValid?: ${kotcoin.isValid(kotcoin)}
            |nonce: ${block.nonce}
            |""".trimMargin()

    )
}

fun IntRange.random() =
        Random().nextInt((endInclusive + 1) - start) +  start
