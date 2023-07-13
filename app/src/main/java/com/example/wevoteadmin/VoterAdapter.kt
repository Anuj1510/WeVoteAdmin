package com.example.wevoteadmin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class VoterAdapter(private val voterList:ArrayList<Vote>,
private val context: Context)
    :RecyclerView.Adapter<VoterAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val candidate:TextView = itemView.findViewById(R.id.CandidateName)
        val partyName:TextView = itemView.findViewById(R.id.PartyName)
        val profile:ImageView = itemView.findViewById(R.id.RegisterProfileImage)
        val voter:ImageView = itemView.findViewById(R.id.ClickedVoterImage)
        val aadhar:ImageView = itemView.findViewById(R.id.VoterAadharCardImage)
        val vote_btn:Button = itemView.findViewById(R.id.Vote_to_candidate)
        val cancel:ImageView = itemView.findViewById(R.id.Cancel_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.voter_list,parent,false)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.candidate.text = voterList[position].name
        holder.partyName.text = voterList[position].party
        Glide.with(context).load(voterList[position].profileImage).into(holder.profile)
        Glide.with(context).load(voterList[position].voterImage).into(holder.voter)
        Glide.with(context).load(voterList[position].aadharImage).into(holder.aadhar)
        holder.vote_btn.setOnClickListener {
            holder.vote_btn.text = "Vote is verified"
        }
        holder.cancel.setOnClickListener {
            holder.vote_btn.text = "Vote is discarded"
            val clickedColor = ContextCompat.getColor(holder.itemView.context, R.color.red)
            holder.vote_btn.setBackgroundColor(clickedColor)
        }
    }

    override fun getItemCount(): Int {
        return voterList.size
    }

}