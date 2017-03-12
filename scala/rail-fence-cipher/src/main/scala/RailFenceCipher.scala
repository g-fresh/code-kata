
object RailFenceCipher {

  // Implemenation of encode
  //
  // Encode text "SECRET":
  //    S . . . E .
  //    . E . R . T
  //    . . C . . .
  //
  // 1. Create pairs of (character, position)
  //    (S,0), (E,1), (C,2), (R,3), (E,4), (T,5)
  // 2. Apply encoding in order to calculate rail for each character
  //    (S,0), (E,1), (C,2), (R,1), (E,0), (T,1)
  // 3. Sort pairs by encoded positions (needs to be stable sort)
  //    (S,0), (E,0), (E,1), (R,1), (T,1), (C,2)
  // 4. Make string from the characters left to right
  //
  // Encoded text: "SEERTC"
  //
  def encode(text: String, rails: Int): String = {
    val encoding = triangle(rails)
    val charPositionsEncoded = for {
      charPositions <- text.zipWithIndex
      (char, pos) = charPositions
    } yield (encoding(pos), char)
    mkString(charPositionsEncoded.sorted)
  }

  // Implemention of decode
  //
  // Decode text "SEERTC"
  //
  // 1. Calculate inverse encoding (rail -> position)
  //    (0,0), (0,4), (1,1), (1,3), (1,5), (2,2)
  // 2. Create pairs of (position, character) by applying inverse encoding
  //    E.g.: (S, (0,0)) => (0,S)
  //          (E, (0,4)) => (4,E)
  //          ...
  // 3. Sort the pairs in order to bring he characters back into the original 
  //    order, then make a string going from left to right
  //    (S,0), (E,1), (C,2), (R,3), (E,4), (T,5)
  //
  // Decoded text: "SECRET"
  //
  def decode(text: String, rails: Int): String = {
    def inverseOf(f: Int => Int) = {
      ( for (x <- 0 until text.length) yield (f(x), x) ).sorted
    }
    val encoding = inverseOf(triangle(rails))
    val charPositionsDecoded = for {
      charPositions <- text.toList.zip(encoding)
      (char, (_, pos)) = charPositions
    } yield (pos, char)
    mkString(charPositionsDecoded.sorted)
  }

  private def triangle(rails: Int) = {
    def triangle(frequency: Int, amplitude: Int)(x: Int): Int =
      amplitude - Math.abs((x % frequency) - amplitude)
    triangle(2 * rails - 2, rails - 1)(_)
  }

  private def mkString(coding: Seq[(Int, Char)]) =
    coding.foldRight(List[Char]()) {
      case ((_, char), acc) => char :: acc
    }.mkString
}

