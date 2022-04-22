import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

// cards: 52 = 4 * 13
// 0..12 + 13..25 + 26..38 + 39..51
// Heart Diamond Club Spade

enum class Suit { Heart, Diamond, Club, Spade }

class Card(val suit: String, number: Int) {
    var name: String
        private set
    init {
        name = when (number) {
            1 -> "Ace"
            11 -> "Jack"
            12 -> "Queen"
            13 -> "King"
            else -> "$number"
        }
    }
    var visible by mutableStateOf(false)
}
val sortedCards: List<Card> = Suit.values().flatMap { suit -> (0..12).map { Card(suit.name, it+1) } }
//val cardsSorted: List<Card> = (0..12).map{ Card("Heart", it+1) } + (13..25).map{ Card("Diamond", it-12) } + (26..38).map{ Card("Club", it-25) } + (39..51).map{ Card("Spade", it-38) }
var cards: MutableList<Card> = sortedCards.toMutableList().also { it.shuffle() }

fun main() {
    console.log("%c Welcome in Poker! ", "color: white; font-weight: bold; background-color: green;")
    //cards.forEach { console.log("${it.suit}\t${it.name}") }

    document.getElementById("Poker_root")?.setAttribute("style", "background-color: green;")
    renderComposable(rootElementId = "Poker_root") {
        Div({ style { backgroundColor(Color.green); padding(25.px) } }) {
            Table {
                for (i in 0..51) {
                    Tr {
                        Td({
                            style { border(2.px, LineStyle.Solid, Color.black); backgroundColor(Color.white); textAlign("center") }
                        }) {
                            Text("${cards[i].suit}-${cards[i].name}")
                        }
                    }
                }
            }
        }
    }
}

