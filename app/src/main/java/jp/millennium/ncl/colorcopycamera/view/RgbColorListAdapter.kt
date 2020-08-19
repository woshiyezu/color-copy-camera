package jp.millennium.ncl.colorcopycamera.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import jp.millennium.ncl.colorcopycamera.R
import jp.millennium.ncl.colorcopycamera.databinding.ItemRgbColorBinding
import jp.millennium.ncl.colorcopycamera.model.RgbColor

class RgbColorListAdapter(private val rgbColorList: ArrayList<RgbColor>) : RecyclerView.Adapter<RgbColorListAdapter.RgbColorViewHolder>() {

    fun updateRgbColorList(newRgbColorList: List<RgbColor>) {
        rgbColorList.clear()
        rgbColorList.addAll(newRgbColorList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RgbColorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemRgbColorBinding>(inflater, R.layout.item_rgb_color, parent, false)
        return RgbColorViewHolder(view)
    }

    override fun getItemCount(): Int = rgbColorList.size


    override fun onBindViewHolder(holder: RgbColorViewHolder, position: Int) {
        holder.bindData(rgbColorList[position])
    }

    class RgbColorViewHolder(var view: ItemRgbColorBinding) : RecyclerView.ViewHolder(view.root), RgbColorClickListener {

        fun bindData(item: RgbColor) {
            view.rgbColor = item
            view.listener = this
        }

        override fun onRgbColorClicked(v: View) {
            /*val uuid = v.dogId.text.toString().toInt()
            val action = ListFragmentDirections.actionDetailFragment()
            action.dogUuid = uuid
            Navigation.findNavController(v).navigate(action)*/
        }
    }

}