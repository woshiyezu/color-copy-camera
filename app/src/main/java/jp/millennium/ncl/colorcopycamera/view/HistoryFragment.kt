package jp.millennium.ncl.colorcopycamera.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import jp.millennium.ncl.colorcopycamera.R
import jp.millennium.ncl.colorcopycamera.viewmodel.RgbColorViewModel
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment() {
    private lateinit var viewModel:RgbColorViewModel
    private val rgbColorListAdapter = RgbColorListAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(RgbColorViewModel::class.java)
        viewModel.refresh()

        rgbColorList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rgbColorListAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.rgbColorList.observe(this, Observer {rgbColorList->
            rgbColorList?.let{
                rgbColorListAdapter.updateRgbColorList(it)
            }
        })
    }
}