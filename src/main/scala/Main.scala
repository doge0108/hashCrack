import com.roundeights.hasher.Implicits._
import scala.util.Random

object Main extends App {
  def generateRandomString(length: Int): String = {
    val chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    Random.alphanumeric.filter(chars.contains(_)).take(length).mkString
  }

  def generateHash(input: String): String = {
    input.md5.hex
  }

  def generateRandomHash(): (String, String) = {
    val randomString = generateRandomString(5) // 5 first might take too long to crack might change after testing
    val hash = generateHash(randomString)
    (randomString, hash)
  }

  def generateCombinations(chars: Seq[Char], length: Int): Iterator[String] = {
    if (length == 0) {
      Iterator("")
    } else {
      for {
        char <- chars.iterator
        rest <- generateCombinations(chars, length - 1)
      } yield char + rest
    }
  }

  def crackHash(targetHash: String, charset: Seq[Char], length: Int): Option[String] = {
    generateCombinations(charset, length).find(str => generateHash(str) == targetHash)
  }

  val (randomString, hash) = generateRandomHash()
  println(s"Generated String: $randomString")
  println(s"Generated Hash: $hash")

//  val userWord = "asdas"
//  val hash = generateHash(userWord)
//  println(s"Hash for '$userWord': $hash")
  val startTime = System.nanoTime()
  val charset = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')
  //val result = crackHash(hash, charset, userWord.length) // change this to input but will use this for now
  val result = crackHash(hash, charset, randomString.length)
  val endTime = System.nanoTime()
  val duration = (endTime - startTime) / 1e9d
  println(s"Hash cracked: $result")
  println(f"Time taken: $duration%.2f seconds")

}