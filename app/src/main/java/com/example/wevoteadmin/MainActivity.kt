package com.example.wevoteadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wevoteadmin.databinding.ActivityMainBinding
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var voteList:ArrayList<Vote>
    private lateinit var ddbRef:DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.voterListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.voterListRecyclerView.setHasFixedSize(true)
        voteList = arrayListOf<Vote>()
        getUserData()

    }

    private fun getUserData() {

        ddbRef = FirebaseDatabase.getInstance().getReference("Voter")
        ddbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){

                    for(userSnapshot in snapshot.children){

                        val user = userSnapshot.getValue(Vote::class.java)
                        voteList.add(user!!)

                        binding.voterListRecyclerView.adapter = VoterAdapter(voteList,this@MainActivity)

                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity,error.toString(),Toast.LENGTH_SHORT).show()
            }

        })

    }
}