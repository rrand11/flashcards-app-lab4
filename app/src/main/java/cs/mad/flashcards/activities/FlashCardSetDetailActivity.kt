package cs.mad.flashcards.activities

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cs.mad.flashcards.R
import cs.mad.flashcards.adapters.FlashcardListAdapter
import cs.mad.flashcards.adapters.FlashcardSetAdapter
import cs.mad.flashcards.entities.getHardcodedFlashcards
/*
Holds add card, delete card, and study set buttons
includes add button and listener - calls insertItem method  in adapter to add item to card list when clicked

XML = activity_flash_card_set_detail
Adapter = FlashcardListAdapter
 */

class FlashCardSetDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_card_set_detail)

        val listrecycler = findViewById<RecyclerView>(R.id.CardList)

        // Access the RecyclerView Adapter and load the data into it
        //val gridLayoutManager = GridLayoutManager(applicationContext, 2, GridLayoutManager.VERTICAL,false)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listrecycler.layoutManager = linearLayoutManager
        listrecycler.setHasFixedSize(true)
        val list = getHardcodedFlashcards()
        listrecycler.adapter = FlashcardListAdapter(list)

        val addbutton = findViewById(R.id.add_card_button) as Button
        // set on-click listener

        addbutton.setOnClickListener {
            (listrecycler.adapter as FlashcardListAdapter).insertItem()
        }

        val studybutton = findViewById(R.id.study_button) as Button
        studybutton.setOnClickListener {
            val intent = Intent(this, StudySetActivity::class.java)
            startActivity(intent)
        }
    }
    }