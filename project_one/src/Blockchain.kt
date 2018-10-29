import java.util.*

object Blockchain {
    val chain = mutableListOf<Block>()
    val latestBlock: Block
        get() = chain.last()

    init {
        chain.add(Block(0, "0", Date().time))
    }

    fun addNewBlock(block: Block) {
        if (isNewBlockValid(block)) chain.add(block)
    }

    private fun isNewBlockValid(block: Block): Boolean {
        if (block.calculateHash() == block.hash) return true
        return false
    }

    fun isValid(blockchain: Blockchain) : Boolean {
        for (block in blockchain.chain){
            if (block.calculateHash() != block.hash) return false
            if (block.index != 0 ) if (block.previousHash != blockchain.chain[block.index - 1].calculateHash()) return false
        }
        return true
    }
}