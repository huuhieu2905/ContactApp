package com.example.gmail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.gmail.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listAdapter: ListAdapter
    private lateinit var listData: ListData
    private var arrayAdapter: ArrayAdapter<String>? = null
    var dataArrayList = ArrayList<ListData?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val nameList = arrayOf(
            "Joshua Kimmich",
            "Stuart Vandervort",
            "Maddison Russel",
            "Halle Morar II",
            "Karelle Simmonich",
            "Hannah Welch",
            "Fanny Framy",
            "Elfrieda Wilsock"
        )
        arrayAdapter =
            ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, nameList)
        val imageList = intArrayOf(
            R.drawable.j,
            R.drawable.s,
            R.drawable.m,
            R.drawable.h,
            R.drawable.k,
            R.drawable.h,
            R.drawable.f,
            R.drawable.e
        )
        val content = intArrayOf(
            R.string.j_detail,
            R.string.s_detail,
            R.string.m_detail,
            R.string.h_detail,
            R.string.k_detail,
            R.string.h2_detail,
            R.string.f_detail,
            R.string.e_detail
        )

        for (i in imageList.indices) {
            listData = ListData(nameList[i], content[i], imageList[i])
            dataArrayList.add(listData)
        }
        listAdapter = ListAdapter(this@MainActivity, dataArrayList)
        binding.listView.adapter = listAdapter
        binding.listView.isClickable = true
        registerForContextMenu(binding.listView)
        binding.listView.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val intent = Intent(this@MainActivity, DetailedActivity::class.java)
                intent.putExtra("name", nameList[i])
                intent.putExtra("content", content[i])
                startActivity(intent)
            }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.main_menu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.menu_call -> {
                Toast.makeText(applicationContext, "Call Menu", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.menu_sms -> {
                Toast.makeText(applicationContext, "SMS Menu", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.menu_email -> {
                Toast.makeText(applicationContext, "Email Menu", Toast.LENGTH_LONG).show()
                return true
            }
            else -> return super.onContextItemSelected(item)
        }
    }
}