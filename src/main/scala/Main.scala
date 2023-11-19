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
    val randomString = generateRandomString(5) // 5 first because might take too long to crack might change after testing
    val hash = generateHash(randomString)
    (randomString, hash)
  }

  val (randomString, hash) = generateRandomHash()
  println(s"Generated String: $randomString")
  println(s"Generated Hash: $hash")

//  val userWord = "test"
//  val hash = generateHash(userWord)
//  println(s"Hash for '$userWord': $hash")
}