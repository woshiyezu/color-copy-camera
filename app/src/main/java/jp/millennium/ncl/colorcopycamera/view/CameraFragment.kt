package jp.millennium.ncl.colorcopycamera.view

import android.graphics.Color
import android.hardware.Camera
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.google.android.material.snackbar.Snackbar

import jp.millennium.ncl.colorcopycamera.R
import jp.millennium.ncl.colorcopycamera.model.RgbColor
import jp.millennium.ncl.colorcopycamera.model.RgbColorDataBase
import jp.millennium.ncl.colorcopycamera.util.copyText
import jp.millennium.ncl.colorcopycamera.util.withColor
import kotlinx.android.synthetic.main.fragment_camera.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import android.view.SurfaceHolder
import kotlinx.android.synthetic.main.fragment_camera.*

class CameraFragment : Fragment(), CoroutineScope by MainScope() {

    private var myCamera: Camera? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_camera, container, false)

        view.captureButton.setOnClickListener {
            val rgbColorCode = "#336544"

            it.copyText(rgbColorCode)

            Snackbar.make(it, "%s copied!!".format(rgbColorCode), Snackbar.LENGTH_LONG)
                .withColor(Color.parseColor(rgbColorCode))
                .show()

            launch {
                activity?.let {
                    RgbColorDataBase(it.application).rgbColorDao().insert(RgbColor(rgbColorCode))
                }
            }
        }

        view.historyButton.setOnClickListener {
            findNavController(it).navigate(CameraFragmentDirections.actionHistoryFragment())
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val holder = mySurfaceView.holder
        holder.addCallback(callback)
    }

    private val callback = object : SurfaceHolder.Callback {
        override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
            myCamera = Camera.open()
            myCamera?.setDisplayOrientation(90);
            myCamera?.setPreviewDisplay(surfaceHolder)
        }

        override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i2: Int, i3: Int) {
            myCamera?.startPreview()
        }

        override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
            myCamera?.release()
            myCamera = null
        }
    }
}