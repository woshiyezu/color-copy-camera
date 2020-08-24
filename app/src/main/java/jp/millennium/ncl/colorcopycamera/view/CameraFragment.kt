package jp.millennium.ncl.colorcopycamera.view

import android.graphics.Color
import android.graphics.ImageFormat
import android.hardware.Camera
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController

import jp.millennium.ncl.colorcopycamera.R
import jp.millennium.ncl.colorcopycamera.model.RgbColor
import jp.millennium.ncl.colorcopycamera.model.RgbColorDataBase
import jp.millennium.ncl.colorcopycamera.util.copyText
import kotlinx.android.synthetic.main.fragment_camera.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import android.view.SurfaceHolder
import androidx.core.graphics.get
import com.androidadvance.topsnackbar.TSnackbar
import jp.millennium.ncl.colorcopycamera.util.ImageUtil
import jp.millennium.ncl.colorcopycamera.util.withColor
import kotlinx.android.synthetic.main.fragment_camera.*

class CameraFragment : Fragment(), CoroutineScope by MainScope() {

    private var camera: Camera? = null
    private var parameters: Camera.Parameters? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_camera, container, false)

        view.captureButton.setOnClickListener {
            val rgbColorCode = "#336544"

            it.copyText(rgbColorCode)

            TSnackbar.make(
                it,
                "%s copied!!".format(rgbColorCode),
                TSnackbar.LENGTH_LONG
            ).withColor(Color.parseColor(rgbColorCode))
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
            camera = Camera.open()
            parameters = camera?.getParameters()
            parameters?.setPreviewFormat(ImageFormat.NV21)
            camera?.setDisplayOrientation(90)
            camera?.setPreviewDisplay(surfaceHolder)
            camera?.setPreviewCallback(object : Camera.PreviewCallback {
                override fun onPreviewFrame(data: ByteArray, camera: Camera) {
                    val previewWidth = camera.getParameters()?.getPreviewSize()?.width!!
                    val previewHeight = camera.getParameters()?.getPreviewSize()?.height!!

                    val bitmap = ImageUtil.getBitmapImageFromYUV(data, previewWidth, previewHeight)
                    val intColor = bitmap[1, 1]
                    val hexColor = "#" + Integer.toHexString(intColor).substring(2)
                }
            })
        }

        override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i2: Int, i3: Int) {
            camera?.startPreview()
        }

        override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
            camera?.release()
            camera = null
        }
    }

}