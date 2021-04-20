package cs.mad.flashcards.adapters

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import cs.mad.flashcards.R
import cs.mad.flashcards.entities.Flashcard

/*
Handles the textview for the list of flashcards

todo: click listener for clicking in textview items - will open the Flashcard Dialog
Flashcard Dialog - shows uneditable text for Term and Def of item clicked
                 - Edit button to open another edit dialog

 */

class FlashcardListAdapter(private val dataset: MutableList<Flashcard>) :
        RecyclerView.Adapter<FlashcardListAdapter.ViewHolder>() {


    // need to get and store a reference to the data set

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // store view references as properties using findViewById on view
        val textView: TextView = view.findViewById(R.id.textView)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        /*
            this is written for you but understand what is happening here
            the layout for an individual item is being inflated
            the inflated layout is passed to view holder for storage

            THE ITEM LAYOUT STILL NEEDS TO BE SETUP IN THE LAYOUT EDITOR
         */

        return ViewHolder(LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.flashcard_item, viewGroup, false))
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataset[position]
        viewHolder.textView.text = item.question

        viewHolder.textView.setOnClickListener {
            // create alert dialog box

            val alert = AlertDialog.Builder(viewHolder.itemView.getContext())
            alert.setMessage(item.question + "\n" + item.answer)
            alert.setTitle("Edit Card")

            // create custom dialog to edit
            alert.setPositiveButton("Edit",DialogInterface.OnClickListener{dialog,id ->
                val builder = AlertDialog.Builder(viewHolder.itemView.getContext())

                val inflater = LayoutInflater.from(viewHolder.itemView.getContext())
                builder.setTitle("With EditText")
                val termview = inflater.inflate(R.layout.custom_term, null)
                val defview = inflater.inflate(R.layout.custom_def, null)
                val editterm = termview.findViewById<EditText>(R.id.edit_term)
                val editdef = termview.findViewById<EditText>(R.id.edit_def)
                builder.setCustomTitle(termview)
                builder.setView(defview)
                builder.setPositiveButton("OK") { dialogInterface: DialogInterface, i: Int ->
                    // show edit was made
                    Snackbar.make(viewHolder.textView, editterm.text.toString(), Snackbar.LENGTH_LONG).show()
                }
                builder.create()
                builder.show()
            })
            alert.show()

            viewHolder.textView.setOnLongClickListener{
                val builder = AlertDialog.Builder(viewHolder.itemView.getContext())

                val inflater = LayoutInflater.from(viewHolder.itemView.getContext())
                builder.setTitle("With EditText")
                val termview = inflater.inflate(R.layout.custom_term, null)
                val defview = inflater.inflate(R.layout.custom_def, null)
                val editterm = termview.findViewById<EditText>(R.id.edit_term)
                val editdef = termview.findViewById<EditText>(R.id.edit_def)
                builder.setCustomTitle(termview)
                builder.setView(defview)
                builder.setPositiveButton("OK") { dialogInterface: DialogInterface, i: Int ->
                    // show edit was made
                    Snackbar.make(viewHolder.textView, editterm.text.toString(), Snackbar.LENGTH_LONG).show()
                }
                builder.create()
                builder.show()
                true
            }
        }
    }

    fun insertItem() {
        val index: Int = dataset.lastIndex + 1
        val newItem = Flashcard("term 42", "answer The Question")
        dataset.add(newItem)
        notifyItemInserted(index)
    }

    override fun getItemCount(): Int {
        // return the size of the data set
        return dataset.size
        //return -1
    }
}
