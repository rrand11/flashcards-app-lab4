package cs.mad.flashcards.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cs.mad.flashcards.R
import cs.mad.flashcards.adapters.FlashcardSetAdapter
import cs.mad.flashcards.entities.Flashcard
import cs.mad.flashcards.entities.FlashcardSet
import cs.mad.flashcards.entities.getHardcodedFlashcardSets
import java.util.*
import kotlin.random.Random.Default.nextInt


/*
===================================================================================================================

     Reference documentation for recyclers: https://developer.android.com/guide/topics/ui/layout/recyclerview

===================================================================================================================
 */

/*
Main Activity is the recycler view that holds the first screen - the list od set of flashcards
It also includes a button to add another set and the listener for when that button is clicked
Add Button clicked calls the add function in FlashcardSetAdapter to add another set to the list

XML = actvity_main
Adapter = FlashcardSetAdapter - holds the set title and listener for when the set is clicked
 */

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.CardSet)


        // Access the RecyclerView Adapter and load the data into it
        val gridLayoutManager = GridLayoutManager(applicationContext, 2, GridLayoutManager.VERTICAL,false)
        recycler.layoutManager = gridLayoutManager
        val list = getHardcodedFlashcardSets()
        recycler.adapter = FlashcardSetAdapter(list)

        val addbutton = findViewById(R.id.add_set_button) as Button
        // set on-click listener

        addbutton.setOnClickListener {
            (recycler.adapter as FlashcardSetAdapter).insertItem()
        }
            // your code to perform when the user clicks on the button
    }
}


//val addbutton = findViewById<Button>(R.id.add_set_button)


//recycler.adapter.((RecyclerView.adapter as FlashcardSetAdapter).add())
//(recycler.adapter as FlashcardSetAdapter).notifyDataSetChanged()



// (recycler.adapter as FlashcardSetAdapter).add()

/*
    connect to views using findViewById
    setup views here - recyclerview,
    add set button - goes into main xml - on click listener here - call add function from adapter

    don't forget to notify the adapter if the data set is changed

    val list = listof...
    recycler.adapter.notifyDataSetChanged()
 */