package cs.mad.flashcards.entities


data class Flashcard(
    val question: String,
    val answer: String
)
{
    var missedbool: Boolean = false
    var skippedbool: Boolean = false
}
fun getHardcodedFlashcards(): MutableList<Flashcard> {
    return mutableListOf(Flashcard("Term 1", "Def 1"),
        Flashcard( "Term 2", "Def 2"),
        Flashcard( "Term 3", "Def 3"),
        Flashcard( "Term 4", "Def 4"),
        Flashcard( "Term 5", "Def 5"),
        Flashcard( "Term 6", "Def 6"),
        Flashcard( "Term 7", "Def 7"),
        Flashcard( "Term 8", "Def 8"),
        Flashcard( "Term 9", "Def 9"),
        Flashcard( "Term 10", "Def 10")
    )
}