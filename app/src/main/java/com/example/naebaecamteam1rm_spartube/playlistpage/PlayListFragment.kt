package com.example.naebaecamteam1rm_spartube.playlistpage

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.naebaecamteam1rm_spartube.databinding.AlertdialogEdittextBinding
import com.example.naebaecamteam1rm_spartube.databinding.FragmentPlaylistBinding


class PlaylistFragment : Fragment() {

    // ViewBinding
    private lateinit var binding : FragmentPlaylistBinding

    // RecyclerView 가 불러올 목록
    private var adapter: PlayListAdapter? = null // RecyclerView 어댑터
    private val data:MutableList<PlayListModel> = mutableListOf() // 목록 데이터를 담을 리스트
    var i = 4

    init{
        instance = this
    }

    companion object{
        private var instance:PlaylistFragment? = null

        // Singleton 패턴을 사용하여 인스턴스를 반환하는 메서드
        fun getInstance(): PlaylistFragment? {
            return instance
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // ViewBinding
        super.onCreate(savedInstanceState)
        binding = FragmentPlaylistBinding.inflate(layoutInflater)
        val view = binding.root
         // ActivityMainBinding을 사용하여 레이아웃을 설정

        initialize() // data 값 초기화
        adapter = PlayListAdapter()
        adapter!!.listData = data
        binding.recyclerView.adapter = adapter // RecyclerView에 어댑터 설정
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext()) // // RecyclerView 레이아웃 매니저 설정

        // FAB 을 누르면 Member + 숫자의 문자열이 data 배열에 추가됨
        binding.fab.setOnClickListener {
            val string = "Member$i"
            i++
            data.add(PlayListModel(string))
            adapter?.notifyDataSetChanged()
        }
    }




    // 목록 데이터를 초기화하는 함수
    private fun initialize(){
        with(data){
            add(PlayListModel("title"))
            add(PlayListModel("thumbnail"))
            add(PlayListModel("description"))
        }
    }

    // 삭제
    fun deleteMember(member: PlayListModel){
        data.remove(member)
        adapter?.notifyDataSetChanged()
    }

    // 수정
    fun editMember(position: Int, member: PlayListModel){

        val builder = AlertDialog.Builder(requireContext())
        val builderItem = AlertdialogEdittextBinding.inflate(layoutInflater)
        val editText = builderItem.editText

        with(builder){
            setTitle("Input Name")
            setMessage("이름을 입력 하세요")
            setView(builderItem.root)
            setPositiveButton("OK"){ _: DialogInterface, _: Int ->
                if(editText.text.toString() != null){
                    member.name = editText.text.toString()
                    data[position] = member
                    adapter?.notifyDataSetChanged()
                }
            }
            show()
        }
    }
}