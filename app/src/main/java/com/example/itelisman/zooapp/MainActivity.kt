package com.example.itelisman.zooapp

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_row.view.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    var listOfAnimals = ArrayList<Animal>()
    var adapter: AnimalsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //load animals
        listOfAnimals.add(Animal("Baboon", "Baboons", R.drawable.baboon, false))
        listOfAnimals.add(Animal("Bulldog", "Bulldogs live in houses", R.drawable.bulldog, false))
        listOfAnimals.add(Animal("Panda", "Pandas live in trees", R.drawable.panda, true))
        listOfAnimals.add(Animal("Swallow", "Swallows live in the sky", R.drawable.swallow_bird, false))
        listOfAnimals.add(Animal("Tiger", "Tigers live in the wild", R.drawable.white_tiger, true))
        listOfAnimals.add(Animal("Zebra", "Zebras live in a trees", R.drawable.zebra, false))
        adapter = AnimalsAdapter(this, listOfAnimals)
        lvAnimalList.adapter = adapter
    }

    fun delete(index:Int){
        listOfAnimals.removeAt(index)
        adapter!!.notifyDataSetChanged()
    }

    fun add(index: Int){
        listOfAnimals.add(index, listOfAnimals[index])
        adapter!!.notifyDataSetChanged()
    }

    inner class AnimalsAdapter: BaseAdapter {
        var listOfAnimals = ArrayList<Animal>()
        var context: Context?=null
        constructor(context:Context, listOfAnimals:ArrayList<Animal>):super(){
            this.listOfAnimals = listOfAnimals
            this.context = context
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val animal = listOfAnimals[position]
            if (animal.isKiller == true) {
                var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflater.inflate(R.layout.killer_animal_row, null)
                myView.tvAnimalName.text = animal.name
                myView.tvAnimalDesc.text = animal.desc
                myView.ivAnimalImage.setImageResource(animal.image!!)
                myView.ivAnimalImage.setOnClickListener {
                    //add(position)
                    val intent = Intent(context, AnimalInfo::class.java)
                    intent.putExtra("name", animal.name!!)
                    intent.putExtra("desc", animal.desc!!)
                    intent.putExtra("image", animal.image!!)
                    context!!.startActivity(intent)
                }
                return myView

            } else {
                var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflater.inflate(R.layout.animal_row, null)
                myView.tvAnimalName.text = animal.name
                myView.tvAnimalDesc.text = animal.desc
                myView.ivAnimalImage.setImageResource(animal.image!!)
                myView.ivAnimalImage.setOnClickListener {
                    //add(position)
                    val intent = Intent(context, AnimalInfo::class.java)
                    intent.putExtra("name", animal.name!!)
                    intent.putExtra("desc", animal.desc!!)
                    intent.putExtra("image", animal.image!!)
                    context!!.startActivity(intent)
                }
                return myView

            }
        }

        override fun getItem(position: Int): Any {
            return listOfAnimals[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfAnimals.size
        }

    }
}
