import java.util.*
import java.security.MessageDigest
import org.bouncycastle.*

class Block(val index: Int,
            val previousHash: String,
            val timestamp: Long = Date().time,
            val data: Date = Date(),
            var nonce: Long = 0,
            difficulty: Int = 1) {

    val hash = mineBlock(difficulty)

    fun calculateHash(): String {
        val input = (index.toString() + previousHash + timestamp + data + nonce).toByteArray()
        return this.hash(input)
    }

    fun hash(bytes: ByteArray): String {
        val sha256 = MessageDigest.getInstance("SHA-256")
        val sha512 = MessageDigest.getInstance("SHA-512")
        val md5 = MessageDigest.getInstance("MD5")

        val part1= md5.digest(bytes)
        val part2 = sha512.digest(bytes)
        val part3 = sha256.digest(bytes)

        return sha512.digest(part1 + part3 + part2).fold("") { str, it -> str + "%02x".format(it)}
    }



    fun mineBlock(difficulty: Int): String {
        var minedHash = calculateHash()

        while (!minedHash.startsWith("0".repeat(difficulty),0)) {
            nonce++
            minedHash = calculateHash()
        }
        return minedHash
    }
}