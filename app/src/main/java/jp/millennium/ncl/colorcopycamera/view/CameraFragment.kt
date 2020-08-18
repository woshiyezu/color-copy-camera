package jp.millennium.ncl.colorcopycamera.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController

import jp.millennium.ncl.colorcopycamera.R
import kotlinx.android.synthetic.main.fragment_camera.view.*

class CameraFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_camera, container, false)

        view.button.setOnClickListener {
            findNavController(it).navigate(CameraFragmentDirections.actionHistoryFragment())
        }

        return view
    }
}