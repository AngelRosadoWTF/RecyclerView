package com.example.recyclerview

import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class User(val fullname: String, var active: Boolean, var batteryLow: Boolean)
class UserAdapter(private val users: MutableList<User>, val activity: AppCompatActivity) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val itemText: TextView
        private val itemCheckBox: CheckBox
        private val itemImage: ImageView

        private lateinit var user: User

        init {
            itemText = view.findViewById(R.id.item_text)
            itemCheckBox = view.findViewById(R.id.item_checkbox)
            itemImage = view.findViewById(R.id.item_image)
            itemImage.setOnClickListener {
                user.batteryLow = !user.batteryLow
                itemImage.setImageResource(
                    if (user.batteryLow) R.drawable.ic_launcher_background
                    else R.drawable.ic_launcher_background
                )
            }
        }

        fun bind(user: User) {
            this.user = user
            itemText.text = user.fullname
            itemCheckBox.isChecked = user.active
            itemImage.setImageResource(
                if (user.batteryLow) R.drawable.ic_launcher_background
                else R.drawable.ic_launcher_background
            )
        }

        // POPUP MENU
        fun createPopupMenuAndShow(onMenuItemClockListener: PopupMenu.OnMenuItemClickListener){
            itemText.setOnClickListener {
                val popup = PopupMenu(view.context, itemText)
                val inflater: MenuInflater = popup.menuInflater
                inflater.inflate(R.menu.context_menu, popup.menu)
                popup.setOnMenuItemClickListener(onMenuItemClockListener)
                popup.show()
                true
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        // FLOATING CONTEXT MENU
        // activity.registerForContextMenu(view)
        // POPUP MENU
        val vh = ViewHolder(view)
        vh.createPopupMenuAndShow {
            when(it.itemId){
                R.id.action_toggle -> {
                    Toast.makeText(parent.context, users[vh.adapterPosition].fullname, Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_delete -> {
                    users.removeAt(vh.adapterPosition)
                    notifyDataSetChanged()
                    true
                }
                else -> true
            }
        }
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(users[position])

    override fun getItemCount(): Int = users.size
}

class MainActivity : AppCompatActivity() {
    private lateinit var userAdapter: UserAdapter

    // FLOATING CONTEXT MENU
    private var itemPosition = -1
    private lateinit var rv: RecyclerView
    private lateinit var users: MutableList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //TOOLBAR
        setSupportActionBar(findViewById(R.id.toolbar))
        rv = findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        rv.setHasFixedSize(true)
        rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        users = mutableListOf(
            User("Angel Efren", true, false),
            User("Anderson Guillermo", false, true),
            User("Alice Johnson", true, false),
            User("Bob Smith", false, true),
            User("Charlie Brown", true, true),
            User("David Wilson", false, false),
            User("Emma Davis", true, false),
            User("Fiona Garcia", false, true),
            User("George Miller", true, true),
            User("Hannah White", false, false),
            User("Ian Harris", true, true),
            User("Julia Martin", false, true),
            User("Kevin Thompson", true, false),
            User("Lily Moore", false, false),
            User("Michael Taylor", true, true),
            User("Nancy Anderson", false, true),
            User("Oscar Martinez", true, false),
            User("Paula Thomas", false, false),
            User("Quinn Jackson", true, true),
            User("Rachel Lee", false, true),
            User("Samuel Perez", true, false),
            User("Tina Adams", false, false),
            User("Ursula Baker", true, true),
            User("Victor Nelson", false, true),
            User("Wendy Scott", true, false),
            User("Xander King", false, false),
            User("Yvonne Green", true, true),
            User("Zachary Lewis", false, false),
            User("Aaron Hall", true, true),
            User("Bethany Young", false, true),
            User("Caleb Allen", true, false),
            User("Diana Wright", false, false)
        )

        userAdapter = UserAdapter(users, this)
        rv.adapter = userAdapter
    }

    //TOOLBAR
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_add -> {
            Toast.makeText(this,"ADD ACTION", Toast.LENGTH_SHORT).show()
            users.add(
                0, User(
                    "Dummy${(1000..9999).random()}",
                    (0..1).random() == 1,
                    (0..1).random() == 1
                )
            )
            userAdapter.notifyDataSetChanged()
            true
        }

        R.id.action_bookmark -> {
            Toast.makeText(this,"BOOKMARK ACTION", Toast.LENGTH_SHORT).show()
            true
        }

        R.id.action_build -> {
            Toast.makeText(this,"BUILD ACTION", Toast.LENGTH_SHORT).show()
            true
        }

        R.id.action_call -> {
            Toast.makeText(this,"CALL ACTION", Toast.LENGTH_SHORT).show()
            true
        }

        R.id.action_connect -> {
            Toast.makeText(this,"CONNECT ACTION", Toast.LENGTH_SHORT).show()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    // FLOATING CONTEXT MENU
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
        val holder = rv.getChildViewHolder(v!!) as UserAdapter.ViewHolder
        itemPosition = holder.adapterPosition
    }

    override fun onContextItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_toggle -> {
            Toast.makeText(this, users[itemPosition].fullname, Toast.LENGTH_SHORT).show()
            true
        }

        R.id.action_delete -> {
            users.removeAt(itemPosition)
            userAdapter.notifyDataSetChanged()
            true
        }

        else -> super.onContextItemSelected(item)
    }
}