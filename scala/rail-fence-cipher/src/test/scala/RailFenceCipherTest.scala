
import org.scalatest.{FunSuite, Matchers}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RailFenceCipherSuite extends FunSuite with Matchers {

  test("test to encode with two rails") {
    val encoded = RailFenceCipher.encode("XOXOXOXOXOXOXOXOXO", 2)
    encoded should be ("XXXXXXXXXOOOOOOOOO")
  }

  test("test to encode with three rails") {
    pending
    val encoded = RailFenceCipher.encode("WEAREDISCOVEREDFLEEATONCE", 3)
    encoded should be ("WECRLTEERDSOEEFEAOCAIVDEN")
  }

  test("test to encode with ending in the middle") {
    pending
    val encoded = RailFenceCipher.encode("EXERCISES", 4)
    encoded should be ("ESXIEECSR")
  }

  test("test to decode with three rails") {
    pending
    val decoded = RailFenceCipher.decode("TEITELHDVLSNHDTISEIIEA", 3)
    decoded should be ("THEDEVILISINTHEDETAILS")
  }

  test("test to decode with five rails") {
    pending
    val decoded = RailFenceCipher.decode("EIEXMSMESAORIWSCE", 5)
    decoded should be ("EXERCISMISAWESOME")
  }

  test("test to decode with six rails") {
    pending
    val decoded = RailFenceCipher.decode("612705927361360836", 6)
    decoded should be ("112358132134558914423337761098715972584418167651094617711286")
  }
}
