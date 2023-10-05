package com.example.naebaecamteam1rm_spartube.playlistpage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naebaecamteam1rm_spartube.databinding.FragmentPlaylistBinding
import com.example.naebaecamteam1rm_spartube.databinding.ItemPlaylistBinding


class PlayListAdapter : RecyclerView.Adapter<Holder>() {


    var listData = mutableListOf<PlayListModel>()

    // ViewHolder를 생성하고 반환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // ViewHolder의 바인딩 객체를 생성합니다.
        val binding = ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }



    // ViewHolder와 데이터를 바인딩합니다.
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val member = listData[position]
        holder.setData(member, position)
    }

    // 데이터 목록의 크기를 반환합니다.
    override fun getItemCount(): Int {
        return listData.size
    }
}

// RecyclerView의 각 항목을 나타내는 ViewHolder 클래스를 정의합니다.
class Holder(val binding: ItemPlaylistBinding) : RecyclerView.ViewHolder(binding.root){

    private val playlistFragment = PlaylistFragment.getInstance()
    var mMember: PlayListModel? = null
    var mPosition: Int? = null

    // ViewHolder가 초기화될 때 호출되는 초기화 블록
    init {
        // 삭제 버튼이 클릭되면 해당 항목을 삭제하는 동작
        binding.btnDelete.setOnClickListener {
            playlistFragment?.deleteMember(mMember!!)
        }

        // 편집 버튼이 클릭되면 해당 항목을 편집하는 동작
        binding.btnEdit.setOnClickListener {
            playlistFragment?.editMember(mPosition!!, mMember!!)
        }
    }

    // ViewHolder의 데이터를 설정하는 메서드
    fun setData(member: PlayListModel, position: Int){
        // ViewHolder의 TextView에 회원 이름을 설정
        binding.textView.text = member.name
        this.mMember = member
        this.mPosition = position
    }
}


