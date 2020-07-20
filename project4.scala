package example
import scala.collection.mutable.ListBuffer

object Word_Chain_Game {


  def readDictionaryFile(): String = {
    val source = scala.io.Source.fromFile("src//dictionary.txt")
    val lines = try source.mkString finally source.close()

    lines
  }

  def random(START: Int, END: Int): Int = {
    val random_number = new scala.util.Random

    START + random_number.nextInt(( END - START) + 1)

  }

  def Game () {
    println("")
    println("#######################")
    println("Start Game")
    print("First player is: ")
    /////////////////////////////////////////////////////////////////////////////

    var win = false;

    // get all words in file and split to array
    val Words = readDictionaryFile().toLowerCase()
    val wordList: List[String] = Words.split(", ").map(_.trim).toList
    var wordListBuff = ListBuffer.empty ++= wordList


    // set a map all element not input
    val inputted_word = scala.collection.mutable.Map[Int, String]()
    var turn = 0;

    // random first player: random 1 for player, 2 for computer
    if(random(0,1) == 1) {
      val A_NUMBER = 97
      val Z_NUMBER = 122
      println("USER")
      println("Please input your word start with => " + random(A_NUMBER, Z_NUMBER).toChar)

    } else {
      val random_number = random(0,wordListBuff.length-1)
      val computer_word = wordListBuff(random_number)
      inputted_word(turn) = "computer " + computer_word // index -> "computer"
      println("COMPUTER")
      println("COMPUTER word: " + computer_word)
      wordListBuff -= computer_word // listBuffer minus 1
      println("Next turn: User")
      print("Input: ")
      turn += 1
    }

    val scanner = new java.util.Scanner(System.in)
    var line = ""
    line = scanner.nextLine().toLowerCase() //pass all to lower case

    var check = line.exists(a => (a.toInt < 97 || a.toInt > 122));

    while(!win) {

      while(!wordListBuff.contains(line) || check) {

       println("Your word must in dictionary file and true format! Please enter again.")
        println("length:" + wordListBuff.length)
        line = scanner.nextLine()

        check = line.exists(a => (a.toInt < 97 || a.toInt > 122))
        println(check)
      }
      inputted_word(turn) = "user " + line // index -> "user"
      turn += 1
      println("User input: " + line)
      wordListBuff -= line


      println(wordListBuff.length)
      println(inputted_word)
//      win = true

      line = ""

      // Computer turn
      val randomNumber = random(0,wordListBuff.length-1)
      val computer_word = wordListBuff(randomNumber)
      inputted_word(turn) = "computer " + computer_word// index -> "computer"
      println("COMPUTER")
      println("COMPUTER word: " + computer_word)
      wordListBuff -= computer_word // listBuffer minus 1
      println(wordListBuff.length)
      println(inputted_word)
      println("Next turn: User")
      print("Input: ")
      turn += 1

    }

//    println("User input: " + line)


  }

  def main(args: Array[String]) {
    Game()
  }
}

