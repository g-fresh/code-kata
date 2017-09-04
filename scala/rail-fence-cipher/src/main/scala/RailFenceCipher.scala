
object RailFenceCipher {

  def encode(text: String, rails: Int): String =
    mapping(text.length, rails) map {
      i => text(i._1)
    } mkString

  def decode(text: String, rails: Int): String =
    mapping(text.length, rails).sorted map {
      i => text(i._2)
    } mkString

  /* Returns the mapping from character index in the original text to the
   * index in the ciphered text.
   *
   * The mapping is calculated by evaluating a triangle wave function in
   * order to compute the corresponding rail for each character position
   * in the original text.
   *
   *    S . . . E .      character  S E C R E T
   *    . E . R . T      pos        0 1 2 3 4 5
   *    . . C . . .      rail       0 1 2 1 0 1
   *
   * The sequence representing this mapping is sorted by rail number
   * and numbered sequentially, dropping the rail numbers.
   * The result is the mapping original to new character index.
   */
  private def mapping(textLength: Int, rails: Int): Seq[(Int, Int)] = {

    def triangle(frequency: Int, amplitude: Int)(x: Int): Int =
      amplitude - Math.abs((x % frequency) - amplitude)

    val amplitude = rails - 1
    val frequency = 2 * amplitude
    val f = triangle(frequency, amplitude)(_)
    val rs = for (idx <- 0 until textLength) yield (f(idx), idx)
    rs.sorted.zipWithIndex map {
      case ((rail, index), newIndex) => (index, newIndex)
    }
  }
}

